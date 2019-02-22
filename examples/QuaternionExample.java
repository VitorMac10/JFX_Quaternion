/*
 * The MIT License
 *
 * Copyright 2018 Vitor Mac.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class QuaternionExample extends Application {

    Box box = new Box(512, 512, 512);
    double zy = 0.0, xy = 0.0;

    public static void main(String... args) {
        Application.launch(args);
    }

    public static double cos(double i) {
        return Math.cos(i * Math.PI / 180);
    }

    public static double sin(double i) {
        return Math.sin(i * Math.PI / 180);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group(box);
        PerspectiveCamera camera = new PerspectiveCamera();
        Scene scene = new Scene(root, 800, 600);
        scene.setCamera(camera);

        primaryStage.setTitle("Quaternion Test");
        primaryStage.setScene(scene);
        primaryStage.show();
        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                zy += 1.0;
                xy += 1.5;
                Matrix4x3 mat = new Matrix4x3();
                Quaternion quat = new Quaternion(1.0 * sin(zy / 2.0), 0.0, 0.0, cos(zy / 2.0));
                Quaternion qt = new Quaternion(0.0, 0.0, -1.0 * sin(xy / 2.0), cos(xy / 2.0));
                box.getTransforms().setAll(mat.rotateGeneric(quat.mul(qt)));
            }
        };
        animator.start();
    }

}
