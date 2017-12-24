package view

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Game : Application() {
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("elements.fxml"))
        primaryStage.title = "White Knight"
        primaryStage.isResizable = false

        val scene = Scene(root, 1200.0, 900.0)
        primaryStage.scene = scene
        scene.stylesheets.add(Game::class.java.getResource("view/styles.css").toExternalForm())
        primaryStage.show()
    }
}