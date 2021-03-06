package com.example.ayush.qapp;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.SharedPreferences;

import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Breakup_Quotes extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    public static String BreakupQuotes[] = {
            "She was an incomplete human yet a complete catastrophe, one that I curse and crave.",
            "First best is falling in love. Second best is being in love. Least best is falling out of love. But any of it is better than never having been in love.",
            "Healing is a choice. It is not an easy one because it takes work to turn around your habits. But keep making the choice and shifts will happen",
            "The worst feeling when someone makes you feel special, then suddenly leaves you hanging and you have to act like you don’t care at all",
            "Never wish them pain. That’s not who you are. If they caused you pain they must have pain inside. Wish them healing",
            "We all go through phases in life. If you are not okay now, it’s okay. You will find your way",
            "Stop trying to change someone who doesn’t want to change. Stop giving chances to someone who abuses your forgiveness. Stop running back to the place where your heart ran from. Stop trusting their words and ignoring their actions. Stop breaking your own heart.",
            "It happens to everyone as they grow up. You find out who you are and what you want, and then you realize that people you’ve known forever don’t see things the way you do. So you keep the wonderful memories, but find yourself moving on.",
            "If you can’t do anything about it then let it go. Don’t be a prisoner to things you can’t change.",
            "Remember that the best relationship is one in which your love for each other exceeds your need for each other.",
            "The poison leaves bit by bit, not all at once. Be patient. You are healing.",
            "Distance doesn’t ruin a relationship. Doubts do.",
            "The past is a place of learning, not a place of living.",
            "Strong enough to walk away, Broken enough to look back",
            "I destroyed it all, bit by bit, Shredded small enough for my heart to hold.",
            "Her knife was flavoured sweet. When she stabbed you were pleased.",
            "If you know the things I went through, the experiences that left scars underneath my skin, you will realize how ugly I have been. ",
            "Never wish death on someone, death offers more peace then they deserve.",
            "The most painful thing is losing yourself in the process of loving someone too much, and forgetting that you are special too.",
            "I feel like I’m waiting for something that isn’t going to happen.",
            "You know you’re fucked when you don’t just think of them at 2:00 am, but also at 2:00 pm",
            "I wish I could show you, when you are lonely or in darkness, the astonishing light of your own being.",
            "You took all that time breaking down the walls to my heart just to choke the life out of it",
            "I’m learning to love the sound of my feet walking away from things not meant for me.",
            "I know this transformation is painful, but you’re not falling apart; you’re just falling into something different, with a new capacity to be beautiful.",
            "Sometimes the most healing thing we can do is remind ourselves over and over and over other people feel this too.",
            "It hurts to know someone will find a way of hurting you even from a distance, and make it feel like it was so close.",
            "Healing is a choice. It is not an easy one because it takes work to turn around your habits. But keep making the choice and shifts will happen.",
            "The loneliest moment in someone’s life is when they are watching their whole life fall apart, and all they can do is stare blankly.",
            "Learn to be alone, and to like it. There’s nothing more freeing and empowering than learning to like your own company.",
            "Doesn’t it scare you that you’ve wasted more than half of your life hating yourself? It should.",
            "One thing I learned a long time ago is that even if you think you’re meant to be with someone, that doesn’t necessarily mean you get to be with them",
            "Sometimes all you can do is lie in bed and hope to fall asleep before you fall apart",
            "I think it’s time I let you go. And that’s so hard to do because a part of me will be in love with you for the rest of my life.  But I can’t do it anymore.",
            "Pain doesn’t just show up in our lives for no reason. It’s a sign that something in our life needs to change",
            "It’s been a long time since I last saw you. And even though my heart doesn’t beat as intensely as it used to; a vision of you could be the fuel it needs.",
            "Sometimes you have to give yourself pep talks like, ‘You’re a badass. Don’t be sad, you got this, and I love you.`",
            "Each day, I slowly think less and less of you. but when I try to love someone else, I am suddenly reminded of you.",
            "Forgiveness doesn’t excuse their behaviour. Forgiveness prevents them from destroying your heart.",
            "I will not be another flower picked for my beauty and left to die. I will be wild, difficult to find, and impossible to forget.",
            "Trust yourself. You have survived a lot. And you will survive whatever is coming.",
            "In between last night’s dreams, I felt your warm breath on my neck and your fingers laced in mine. It’s only in these moments, when I’m too tired to be in denial, that my aching for you reveals itself.",
            "You were the melancholic kiss on my dried cheeks at 4 am when tears had long gone away.",
            "Often, we get crushes on others not because we truly love and understand them, but to distract ourselves from our suffering.",
            "Solitude isn’t loneliness. Solitude is when the entire serene universe seems to surround and hold you quietly",
            "Sometimes something sad happens, but it is the beginning of the most beautiful thing in your life",
            "I don’t ask anyone to stay. Not anymore. The phone may tremble in my hand. But never again. Will I beg for someone to love me",
            "How many times can you break my heart before I finally walk away?",
            "If only our eyes saw souls instead of bodies, how very different our ideals of beauty would be.",
            "Your mother did not spend nine months growing you a fierce heart just so it could stop beating the moment love leaves it",
            "I hope you’re drowning in your tears like I did. I hope you’re in agony that seems endless. I hope you feel like you’ve lost everything like I did.",
            "The fact that my absence doesn’t bother you even the slightest makes me sad to wonder if my presence ever mattered at all in the first place."
    };
    InterstitialAd mInterstitialAd;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakup__quotes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadData();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        MobileAds.initialize(this, "ca-app-pub-1203140157527769~6707095223");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1203140157527769/2197128284");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setBackgroundResource(Settings.backgroundId);
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewBreakupQuotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(BreakupQuotes,this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            startActivity(new Intent(this,Favorite_Quotes.class));
            finish();
            return true;
        }


        if(id == R.id.action_help){
            startActivity(new Intent(this,Help.class));
            return true;
        }

        if(id == R.id.action_feedback){
            String subject = "Feedback";
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"ayush.bherwani1998@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,"");
            if(intent.resolveActivity(getPackageManager())!=null){
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"No Email App Detected",Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        if(id == R.id.action_settings){
            startActivity(new Intent(Breakup_Quotes.this,Settings.class));
            finish();
            return true;
        }
        if(id == R.id.action_rateus){
            startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.ayushbherwani.ayush.qapp")));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        ArrayList<String> arrayList = new ArrayList<String>(Favorite_Quotes.FavoriteQuotes);
        String json = gson.toJson(arrayList);
        editor.putString("favoriteQuotes",json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences preferences = getSharedPreferences("Settings",MODE_PRIVATE);
        Settings.backgroundId = preferences.getInt("backgroundId",R.color.default_color);
        Settings.textSize = preferences.getInt("textSize",14);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("favoriteQuotes", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arrayList;
        arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            Favorite_Quotes.FavoriteQuotes = new LinkedList<>();
        } else {
            Favorite_Quotes.FavoriteQuotes = new LinkedList<>(arrayList);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
