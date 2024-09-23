package com.hilmiyafia.facegen.service;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@Component
public class ImageService {

    private OrtEnvironment environment;
    private OrtSession session;

    public ImageService() {
        try {
            environment = OrtEnvironment.getEnvironment();
            OrtSession.SessionOptions options = new OrtSession.SessionOptions();
            try (InputStream stream = getClass().getResourceAsStream("/resources/model.onnx")) {
                if (stream != null) {
                    byte[] model = stream.readAllBytes();
                    session = environment.createSession(model, options);
                }
            }
        } catch (IOException | OrtException ignored) {
        }
    }

    public byte[] getImage(float[] values) {
        try {
            OnnxTensor tensor = OnnxTensor.createTensor(environment, values);
            OrtSession.Result output = session.run(Collections.singletonMap("input", tensor));
            int[] pixels = (int[]) output.get(0).getValue();
            BufferedImage image = new BufferedImage(128, 128, BufferedImage.TYPE_INT_RGB);
            image.setRGB(0, 0, 128, 128, pixels, 0, 128);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", stream);
            stream.flush();
            return  stream.toByteArray();
        } catch (IOException | OrtException ignored) {
            return null;
        }
    }
}
