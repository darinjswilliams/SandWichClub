package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView mDescriptionTextView;
    private TextView mIngredientsTextView;
    private TextView mAlsoKnowAsTextView;
    private TextView mOriginTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv =  (ImageView) findViewById(R.id.image_iv);
        mDescriptionTextView = (TextView) findViewById(R.id.description_tv);
        mIngredientsTextView = (TextView) findViewById(R.id.ingredients_tv);
        mAlsoKnowAsTextView = (TextView) findViewById(R.id.also_known_tv);
        mOriginTextView = (TextView) findViewById(R.id.place_of_orgin);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);


        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich){
    //TODO update method to populate UI
//    TextView alsoKnowAsTitle = (TextView) findViewById(R.id.also_know_as_title);

//    List<String> aknowAsList = sandwich.getAlsoKnownAs();

//    if(aknowAsList.size() == 0) {
//        alsoKnowAsTitle.setVisibility(View.GONE);
//        mAlsoKnowAsTextView.setVisibility(View.GONE);
//    }
    mOriginTextView.setText(sandwich.getPlaceOfOrigin());

    if(sandwich.getAlsoKnownAs()  != null ){
        for(int i = 0; i < sandwich.getAlsoKnownAs().size(); i++){
            mAlsoKnowAsTextView.append(sandwich.getAlsoKnownAs().get(i) + " ");
        }

    }


    for(int x = 0; x < sandwich.getIngredients().size(); x++){
        mIngredientsTextView.append(sandwich.getIngredients().get(x) + " ");
    }

    mDescriptionTextView.setText(sandwich.getDescription());
    }

}
