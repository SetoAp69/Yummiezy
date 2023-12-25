package com.excal.yummiezy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.excal.yummiezy.R
import com.excal.yummiezy.data.Recipe
import com.excal.yummiezy.data.RecipeAdapter
import com.excal.yummiezy.databinding.ActivityMainBinding
import com.excal.yummiezy.databinding.ActivityRecipeDetailBinding

class MainActivity : AppCompatActivity(), RecipeAdapter.onItemClick {
    private lateinit var rvRecipe: RecyclerView
    private lateinit var binding : ActivityMainBinding
    private val list=ArrayList<Recipe>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvRecipe=(binding.rvRecipe)

        list.addAll(getListRecipe())
        showRecipe()
        binding.btnAbout.setOnClickListener(){
            var intent: Intent = Intent(this@MainActivity,AboutActivity::class.java)
            startActivity(intent)
        }


    }
    private fun getListRecipe():ArrayList<Recipe>{
        val listRecipe=ArrayList<Recipe>()
        val title=resources.getStringArray(R.array.recipe_title_array)
        Log.i("MainActivity","1")
        val img=resources.obtainTypedArray(R.array.recipe_image)
        Log.i("MainActivity","1")
        var index=0;
        for (i in title.indices){
            val recipe=Recipe(title[i],img.getResourceId(i,-1),index)

            Log.i("MainActivity","1")

            listRecipe.add(recipe)
            index++
        }
        return listRecipe
    }

    private fun showRecipe(){
        rvRecipe.layoutManager=GridLayoutManager(this@MainActivity,2, VERTICAL,false)
        val listRecipeAdapter= RecipeAdapter(list)
        rvRecipe.adapter=listRecipeAdapter
        listRecipeAdapter.listener=this@MainActivity
    }
    override fun setOnItemClick(view: View, recipe: Recipe) {

        val data=recipe
        var intent: Intent = Intent(this@MainActivity,RecipeDetailActivity::class.java)
        intent.putExtra("id",data.id)
        intent.putExtra("title",data.Name)
        intent.putExtra("img",data.imgUrl)
        startActivity(intent)
    }
}