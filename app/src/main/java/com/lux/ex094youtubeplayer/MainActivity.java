package com.lux.ex094youtubeplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.lux.ex094youtubeplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //youtube 전용 player - 구글 개발자 사이트의 가이드 참고
    //Youtuve PalyER API - 사이트에서 .zip 압축문서를 다운받아 압축해제하여
    //내 프로젝트의 libs에 복붙
    //Gradle 빌드 프로그램을 이용해서 라이브러리를 앱에 적용

    ActivityMainBinding binding;

    YouTubePlayerFragment youTubePlayerFragment;
    YouTubePlayerFragment youTubePlayerFragment2;
    YouTubePlayerFragment youTubePlayerFragment3;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       binding=ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

       //xml 에 있는 YoutubePalyerFragment 참조 - androidX 로 만들지 않아서 androidX 를 사용하지 않음.
       FragmentManager fragmentManager=getFragmentManager();
       youTubePlayerFragment= (YouTubePlayerFragment) fragmentManager.findFragmentById(R.id.youtube_fragment);

       //youtube player 초기화 - 로딩하기.
       youTubePlayerFragment.initialize("first", new YouTubePlayer.OnInitializedListener() {
           @Override
           public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //유튜브 비디오 id 지정(url 아님!) - youtube 사이트에서 동영상 페이지 url 의 v= 옆에 있는 식별글씨
               youTubePlayer.loadVideo("tfJntocV3HE");  //로딩이 완료되면 자동 실행
           }

           @Override
           public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
               Toast.makeText(MainActivity.this, "error \n"+youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
           }
       });
       youTubePlayerFragment2= (YouTubePlayerFragment) fragmentManager.findFragmentById(R.id.youtube_fragment2);
       youTubePlayerFragment2.initialize("second", new YouTubePlayer.OnInitializedListener() {
           @Override
           public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
               youTubePlayer.cueVideo("kSvHU6uAXfc");  //로딩이 완료되어도 대기
           }

           @Override
           public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

           }
       });
       youTubePlayerFragment3= (YouTubePlayerFragment) fragmentManager.findFragmentById(R.id.youtube_fragment3);
       youTubePlayerFragment3.initialize("third", new YouTubePlayer.OnInitializedListener() {
           @Override
           public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
               youTubePlayer.cueVideo("uh4dTLJ9q9o");
           }

           @Override
           public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

           }
       });

       binding.youtubeThumbnail.initialize("thumb", new YouTubeThumbnailView.OnInitializedListener() {
           @Override
           public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
               youTubeThumbnailLoader.setVideo("TOtPb4yXUtc");
           }

           @Override
           public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

           }
       });
    }

}