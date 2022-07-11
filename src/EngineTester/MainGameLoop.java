package EngineTester;

import org.lwjgl.opengl.Display;
import rengerEngine.DisplayManager;
import rengerEngine.Loader;
import rengerEngine.RawModel;
import rengerEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices = {
                // first triangle:
                -0.5f, 0.5f, 0f,  //vertex0
                -0.5f, -0.5f, 0f, //vertex1
                 0.5f, -0.5f, 0f,  //vertex2
                 0.5f, 0.5f, 0f, //vertex3
        };

        int[] indices ={
                0,1,3, //Top-Left Triangle
                3,1,2 //Bottom-right triangle
        };
        RawModel model = loader.loadToVAO(vertices,indices);

        while(!Display.isCloseRequested()){

            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            DisplayManager.updateDisplay();
        }
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();


    }
}
