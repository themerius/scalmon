package htwg.scalmon.view

import htwg.scalmon.BuildInfo
import htwg.scalmon.model.Model
import htwg.scalmon.controller.Controller

class GUI(_model: Model, _controller: Controller) extends View(_model, _controller) {
  def update = println("GUI update")

  def show = {
    val myFrame = new ScalmonFrame
    myFrame.visible = true
  }
}

class ScalmonFrame extends swing.Frame {
  title = BuildInfo.name + " " + BuildInfo.version
  contents = new swing.Label("scalmon GUI")
}