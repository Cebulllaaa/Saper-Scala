import scala.util.Random
import scala.swing.*
import java.awt.Dimension


object Main extends App{

  val backend = new Backend()
  val frontend = new Frontend()

  class Backend{
    val board : Set[Int] = ((1 to 10).map( x => Random.between(1,65))).toSet

    println(board)
  }

  class Frontend extends MainFrame{
    size = new Dimension(640, 640)
    title = "SAPER"
    visible = true
    val points = new Label("0")

    contents = new BoxPanel(Orientation.Vertical){
      contents += new BoxPanel(Orientation.Horizontal){
        contents += new NewGameButton()
      }
      (1 to 8).map(x =>
        contents +=  new BoxPanel(Orientation.Horizontal){
          (1 to 8).map(y => contents += new saperField((x-1)*8 + y) )
          }
        )
      contents += new BoxPanel(Orientation.Horizontal){
        contents += points
        }
      }

    class saperField (val position : Int ) extends Button{
      minimumSize = new Dimension(80,80)
      preferredSize = new Dimension(80,80)
      maximumSize = new Dimension(80,80)
      lazy val fieldValue = Random.between(0,5)
      action = new Action("") {
        def apply() =
          if backend.board.contains(position) then
            action = new Action("Bum") {
              def apply() = {
                Dialog.showMessage(Label.apply(), s"You got ${points.text} score", "Game Over")
                NewGameButton.apply().newGame()
              }
            }
            action.apply()
          else
            action = new Action(fieldValue.toString) {
              def apply() = {}
            }
            points.text = (points.text.toInt + fieldValue).toString
      }

      }
    }
    class NewGameButton extends Button{
      def newGame() ={
        val newContent = new Frontend()
        newContent.visible = false
        frontend.contents = newContent.contents.apply(0)
        newContent.dispose()
      }
      action = new Action("New Game"){
        def apply() = newGame()
      }
    }
  }