import scala.util.Random
import scala.swing.*
import java.awt.Dimension


object Main extends App{

  val backend = new Backend()
  val frontend = new Frontend()

  class Backend{
    val board : Set[Int] = ((1 to 10).map( x => Random.between(0,64))).toSet

    println(board)
  }

  class Frontend extends MainFrame{
    size = new Dimension(640, 640)
    title = "SAPER"
    visible = true
    contents = new BoxPanel(Orientation.Vertical){
      (1 to 8).map(x =>
        contents += new BoxPanel(Orientation.Horizontal){
          (1 to 8).map(y => contents += new myButton((x-1)*8 + y) )
          }
        )
      }


    class myButton (val position : Int ) extends Button{
      minimumSize = new Dimension(80,80)
      preferredSize = new Dimension(80,80)
      maximumSize = new Dimension(80,80)
      action = new Action(position.toString) {
        def apply() =
          if backend.board.contains(position) then println("Buum")
          else println("Point")
      }

    }
  }
}
