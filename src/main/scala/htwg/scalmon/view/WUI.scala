package htwg.scalmon.view

import htwg.scalmon.model._
import htwg.scalmon.controller._
import htwg.scalmon.view.wui._

import akka.actor.{ ActorSystem, Props }
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

class WUI(_model: Model, _controller: Controller) extends View(_model, _controller) {
  implicit val system = ActorSystem("on-spray-can")
  val service = system.actorOf(Props[ServiceActor], "demo-service")
  implicit val timeout = Timeout(5.seconds)

  Bypass.model = model
  Bypass.controller = controller

  def update(info: Option[AbilityInfo]) = {
    model.state match {
      case Init(_) => Bypass.inInitPhase = true
      case Exited  => system.shutdown
      case _ => {
        Bypass.inInitPhase = false
        var showInfo = info.orElse(Bypass.lastInfo)
        Bypass.lastInfo = info

        Bypass.battlefieldText = model.state.isInstanceOf[GameOver] match {
          case true =>
            Bypass.restartButton = true
            val winner = model.state.asInstanceOf[GameOver].winner
            "Game over! The winner is " + (if (winner != null) winner.name else "None") + "!"
          case false =>
            Bypass.restartButton = false
            showInfo.getOrElse(null) match {
              case a: AttackInfo => s"${a.attacker.name} attacks ${a.victim.name} with a damage of ${a.damage}."
              case h: HealInfo   => s"${h.healer.name} healed ${h.cured.name} with ${h.healthPoints} health points."
              case _             => ""
            }
        }
      }
    }
  }

  def show = IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
}