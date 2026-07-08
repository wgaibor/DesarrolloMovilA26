package com.example.veterinarialemas.utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * Utilidad para subir imágenes a ImgBB (servicio gratuito de almacenamiento de imágenes).
 *
 * ImgBB ofrece almacenamiento gratuito de imágenes con las siguientes características:
 * - Completamente gratuito
 * - No requiere registro ni API key para uso básico
 * - URLs directas a las imágenes
 * - Sin límite de almacenamiento (con límites razonables de uso)
 *
 * Documentación: https://api.imgbb.com/
 *
 * @author Cafeteria App
 * @version 1.0
 */
public class ImageUploader {
    private static final MediaType IMAGES_JPEG = MediaType.parse("image/jpeg");
    private static final OkHttpClient client = new OkHttpClient();

    /**
     * API Key de ImgBB (opcional, pero recomendado para mejor rendimiento).
     * Puedes obtener una API key gratuita en: https://api.imgbb.com/
     * Si no proporcionas una API key, ImgBB funcionará pero con límites más estrictos.
     *
     * Para obtener una API key gratuita:
     * 1. Ve a https://api.imgbb.com/
     * 2. Haz clic en "Get API Key"
     * 3. Completa el formulario (es gratis)
     * 4. Copia tu API key
     * 5. Reemplaza "TU_API_KEY_AQUI" con tu API key real (o déjalo vacío para usar sin API key)
     */
    private static final String IMGBB_API_KEY= "3d0250110a6fe1e0b5556bd222f24629"; // No compartir por nada del mundo esta key

    /**
     * URL del endpoint de ImgBB para subir imagenes
     */
    private static final String IMGBB_UPLOAD_URL = "https://api.imgbb.com/1/upload";


}
