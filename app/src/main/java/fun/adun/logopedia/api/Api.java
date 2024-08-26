package fun.adun.logopedia.api;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

interface Api {

    @Multipart
    @POST("///////")
    Call<ResponseBody> uploadFile(
            @Part MultipartBody.Part file
    );

    static final Api instance = create();

    static Api create() {
        return new Retrofit.Builder()
                .baseUrl("URLLLLLLLLLLLLLLLLLL") // !!!!!!!!!!!!!!!!!!!!!!
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
    }

    public static Api getInstance() {
        return instance;
    }
}
