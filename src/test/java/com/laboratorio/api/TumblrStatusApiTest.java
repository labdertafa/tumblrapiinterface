package com.laboratorio.api;

import com.laboratorio.clientapilibrary.utils.ReaderConfig;
import com.laboratorio.tumblr.TumblrStatusApi;
import com.laboratorio.tumblr.impl.TumblrStatusApiImpl;
import com.laboratorio.tumblr.model.TumblrStatusResponse;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * author Rafael
 * version 1.0
 * created 01/05/2025
 * updated 02/05/2025
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TumblrStatusApiTest {
    private TumblrStatusApi statusApi;
    private static String postId;

    @BeforeEach
    public void initTest() {
        ReaderConfig config = new ReaderConfig("config/tumblr_api.properties");
        String accessToken = config.getProperty("test_access_token");
        String test_blog = config.getProperty("test_blog");

        this.statusApi = new TumblrStatusApiImpl(accessToken, test_blog);
    }

    @Test @Order(1)
    public void postTextTest() {
        String texto = "Este texto proviene de un test unitario construido con JUNIT 5. #FelizDomingo para probar mi librería Tumblr #SiguemeYTeSigo";

        TumblrStatusResponse response = this.statusApi.postStatus(texto);
        postId = response.getResponse().getId_string();

        assertNotNull(response.getMeta());
        assertNotNull(response.getResponse());
    }

    @Test @Order(2)
    public void deleteTextPost() {
        boolean result = this.statusApi.deleteStatus(postId);

        assertTrue(result);
    }

    @Test @Order(3)
    public void postImageTest() {
        String texto = "Este el el circuito #FelizDomingo de Bélgica en el año 1950 #SiguemeYTeSigo #Followback";
        String imagePath = "C:\\Users\\rafa\\Pictures\\Formula_1\\Spa_1950.jpg";

        TumblrStatusResponse response = this.statusApi.postStatus(texto, imagePath);
        postId = response.getResponse().getId_string();

        assertNotNull(response.getMeta());
        assertNotNull(response.getResponse());
    }

    @Test @Order(4)
    public void deleteImagePost() {
        boolean result = this.statusApi.deleteStatus(postId);

        assertTrue(result);
    }

    @Test @Order(5)
    public void postVideoTest() {
        String texto = "Tengo publicado en Youtube un tutorial que explica como instalar #Kafka Con #Docker.\n" +
                "Es una plataforma de streaming distribuida utilizada en aplicaciones de datos en tiempo real.\n" +
                "\n" +
                "https://youtu.be/4uuReS6qG6Q\n" +
                "\n" +
                "#SiguemeYTeSigo #Followback";
        String imagePath = "C:\\Users\\rafa\\Pictures\\Formula_1\\Spa_1950.jpg";

        TumblrStatusResponse response = this.statusApi.postStatus(texto, imagePath);
        postId = response.getResponse().getId_string();

        assertNotNull(response.getMeta());
        assertNotNull(response.getResponse());
    }

    @Test @Order(6)
    public void deleteVideoPost() {
        boolean result = this.statusApi.deleteStatus(postId);

        assertTrue(result);
    }
}
