package fun.adun.logopedia;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import java.io.File;

public class AudioRequestSender {

    private interface MyApi {
        @Multipart
        @POST("/audio")
        Call<Integer> sendAudio(@Part("audio_file") MultipartBody.Part audioFile);
    }

    public static void main(String[] args) {

        // Базовый URL сервера
        String baseUrl = "https://your-server-url.com";

        // Создаем экземпляр Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Создаем интерфейс API
        MyApi myApi = retrofit.create(MyApi.class);

        // Путь к аудиофайлу
        String audioFilePath = "/path/to/your/audio.wav";

        // Создаем объект File
        File audioFile = new File(audioFilePath);

        // Создаем RequestBody для аудиофайла
        RequestBody requestFile = RequestBody.create(MediaType.parse("audio/gp3"), audioFile);

        // Создаем MultipartBody.Part для аудиофайла
        MultipartBody.Part body = MultipartBody.Part.createFormData("audio_file", audioFile.getName(), requestFile);

        // Выполняем запрос
        Call<Integer> call = myApi.sendAudio(body);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    Integer result = response.body();
                } else {
                    try {
                        ////
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                // Обрабатываем ошибки
                System.out.println("Ошибка: " + t.getMessage());
            }
        });
    }
}