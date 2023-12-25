package com.excal.yummiezy.ui

import android.app.Notification.Action
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.excal.yummiezy.R
import com.excal.yummiezy.data.Ingredient
import com.excal.yummiezy.data.IngredientAdapter
import com.excal.yummiezy.data.Instruction
import com.excal.yummiezy.data.InstructionAdapter
import com.excal.yummiezy.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var rvIngredient:RecyclerView
    private lateinit var rvInstruction:RecyclerView
    private lateinit var binding : ActivityRecipeDetailBinding
    private var serving:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        val recipeId:Int=intent.getIntExtra("id",0)
        val recipeTitle:String=intent.getStringExtra("title").toString()
        val imgId:Int=intent.getIntExtra("img",0)



        super.onCreate(savedInstanceState)
        binding= ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setImageResource(imgId)
        binding.tvTitle.text=recipeTitle
        rvIngredient=binding.rvIngredients
        rvInstruction=binding.rvInstructions

        binding.btnShare.setOnClickListener(){
            val shareIntent: Intent=Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val shareBody = recipeTitle
            val shareSubject = recipeId
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject)

            startActivity(Intent.createChooser(shareIntent, "Share using"))

        }

        binding.btnBack.setOnClickListener(){
            var intent: Intent = Intent(this@RecipeDetailActivity,MainActivity::class.java)
            startActivity(intent)

        }

        var servingValue=binding.serving
        var minButton = binding.btnMinus
        var addButton = binding.btnAdd
        var favButton=binding.btnFav
        var isFavorite=false
        favButton.isSelected=isFavorite

        favButton.setOnClickListener(){
            if(isFavorite){
                isFavorite=false
                favButton.setBackgroundColor(Color.TRANSPARENT)
                Toast.makeText(this@RecipeDetailActivity, "Removed from favorite", Toast.LENGTH_SHORT ).show()

            }else{
                isFavorite=true
                favButton.setBackgroundColor(ContextCompat.getColor(this,R.color.light_purple))
                Toast.makeText(this@RecipeDetailActivity, "Added to favorite", Toast.LENGTH_SHORT ).show()


            }
        }
        servingValue.text="1"


        minButton.setOnClickListener(){
            if(serving>1){
                serving--
                servingValue.text=serving.toString()
                Log.i("RecipeActivity","$serving")
                showIngredients(getIngredients(recipeId,serving))
            }
            Log.i("RecipeActivity","$serving")
        }
        addButton.setOnClickListener(){
                serving++
                servingValue.text=serving.toString()
                Log.i("RecipeActivity","$serving")
                showIngredients(getIngredients(recipeId,serving))

            Log.i("RecipeActivity","$serving")
        }



        showIngredients(getIngredients(0))
        showInstructions(getInstruction(0))
    }

    private fun getIngredients( id:Int,  serving: Int =1):ArrayList<Ingredient>{
        val ingredientArrayId=resources.obtainTypedArray(R.array.recipe_array)
        Log.d("RecipeDetailActivity", "Got the array Ids")
        val ingredientArray =arrayListOf<List<String>>()
        for(id in 0 until ingredientArrayId.length()){
            Log.d("RecipeDetailActivity", "listing nested value")
            val ingredientResourceId=ingredientArrayId.getResourceId(id,0)
            val ingredientData=resources.getStringArray(ingredientResourceId).toList()
            Log.d("RecipeDetailActivity", "listing nested value (success)")

            ingredientArray.add(ingredientData)
        }
        Log.d("RecipeDetailActivity", "Listed all the ingredients")
        val ingredientList=ArrayList<Ingredient>()
        val ingredientAmountList=getAmount(id)
        for(i in ingredientArray[id].indices){
            val ingredient=Ingredient(ingredientAmountList.get(i)*serving,ingredientArray[id].get(i))
            ingredientList.add(ingredient)
        }
        return ingredientList
    }
    private fun getAmount(id: Int):ArrayList<Double>{
        val amountArrayId=resources.obtainTypedArray(R.array.recipe_amount_array)
        Log.d("RecipeDetailActivity", "Got the array Ids")
        val ingredientAmountArray =arrayListOf<List<String>>()
        for(id in 0 until amountArrayId.length()){
            Log.d("RecipeDetailActivity", "listing nested value")
            val ingredientAmountResourceId=amountArrayId.getResourceId(id,0)
            val ingredientAmountData=resources.getStringArray(ingredientAmountResourceId).toList()
            Log.d("RecipeDetailActivity", "listing nested value (success)")
            ingredientAmountArray.add(ingredientAmountData)
        }
        val ingredientAmountList=ArrayList<Double>()
        for (i in ingredientAmountArray[id].indices){
            val amount=ingredientAmountArray[id].get(i).toDouble()
            ingredientAmountList.add(amount)
        }
        return ingredientAmountList
    }

    private fun getInstruction(id:Int):ArrayList<Instruction>{
        val instructionArrayId=resources.obtainTypedArray(R.array.instruction_array)
        val instructionArray=arrayListOf<List<String>>()
        for(id in 0 until instructionArrayId.length()){
            val instructionResourceId=instructionArrayId.getResourceId(id,0)
            val instructionData=resources.getStringArray(instructionResourceId).toList()
            instructionArray.add(instructionData)
        }
        val instructionList=ArrayList<Instruction>()
        for(i in instructionArray[id].indices){
            val instruction=Instruction(instructionArray[id].get(i))
            instructionList.add(instruction)
        }
        return instructionList
    }

    private fun showIngredients(list:ArrayList<Ingredient>,serving:Int=1){
        rvIngredient.layoutManager=LinearLayoutManager(this)
        val listIngredientAdapter= IngredientAdapter(list)
        rvIngredient.adapter=listIngredientAdapter



    }

    private fun showInstructions(list:ArrayList<Instruction>){
        rvInstruction.layoutManager=LinearLayoutManager(this)
        val listInstructionAdapter= InstructionAdapter(list)
        rvInstruction.adapter=listInstructionAdapter
    }
}