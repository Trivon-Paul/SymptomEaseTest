package com.example.navdrawerapp;

//class MyRecyclerAdapter(private val myDataSet: Array<String>):
//        RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder.
//class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
  //      val personName=itemView.findViewById<TextView>(R.id.person_name)
  //      val personAge=itemView.findViewById<TextView>(R.id.person_age)
 //       }
// Create new views (invoked by the layout manager)
  //      override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):MyViewHolder{
 //       val view=LayoutInflater.from(parent.context)
 //       .inflate(R.layout.row_item,parent,false)
 //       return MyViewHolder(view)
 //       }
// Replace the contents of a view (invoked by the layout manager)
 //       override fun onBindViewHolder(holder:MyViewHolder,position:Int){
 //       holder.personName.text=myDataSet[position]
 //       holder.personAge.text="Age = $position"
 //       }
// Return the size of your dataset (invoked by the layout manager)
 //       override fun getItemCount():Int{
//        return myDataSet.size
//       }
//        }

/**
 * DIRECTLY FROM RECYCLER VIEW SLIDES^ Could get us started with our symptomEase recycler view adapter.
 * The layout manager binds the view holder to
 * its data. It does this by calling the
 * adapter's onBindViewHolder() method, and
 * passing the view holder's position in
 * the RecyclerView.
 * The onBindViewHolder() method needs to
 * fetch the appropriate data, and use it to fill
 * in the view holder's layout. For example, if
 * the RecyclerView is displaying a list of
 * names, the method might find the
 * appropriate name in the list, and fill in the
 * view holder's TextView widget
 */






