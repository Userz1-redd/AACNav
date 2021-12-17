package com.example.kotlinhello.Home


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinhello.App
import com.example.kotlinhello.Constants.RESPONSE_STATE
import com.example.kotlinhello.Constants.SharedPreferenceManager
import com.example.kotlinhello.Constants.toSimpleString
import com.example.kotlinhello.Home.BoardRecyclerView.BoardRecyclerViewAdapter
import com.example.kotlinhello.Home.BoardRecyclerView.BoardSearchHistoryRecyclerViewAdapter
import com.example.kotlinhello.Home.BoardRecyclerView.IBoardRecyclerView
import com.example.kotlinhello.Home.BoardRecyclerView.ISearchHistoryRecyclerView
import com.example.kotlinhello.R
import com.example.kotlinhello.databinding.FragmentBoardBinding
import com.example.kotlinhello.model.NoticeResponseDTO
import com.example.kotlinhello.model.SearchBoardData
import com.example.kotlinhello.retrofit.RetrofitManager
import java.util.*
import kotlin.collections.ArrayList


class BoardFragment : Fragment(),SearchView.OnQueryTextListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener
    ,ISearchHistoryRecyclerView, IBoardRecyclerView {
    private val TAG = "TAG"
    private var mBinding : FragmentBoardBinding? = null

    //검색기록 배열
    private var searchHistory = ArrayList<SearchBoardData>()

    //게시판 데이터
    private var boardList  = ArrayList<NoticeResponseDTO>()

    //어댑터
    private lateinit var boardRecyclerViewAdapter : BoardRecyclerViewAdapter
    private lateinit var boardSearchHistoryRecyclerViewAdapter: BoardSearchHistoryRecyclerViewAdapter
    //서치뷰
    private lateinit var mySearchView : SearchView
    private lateinit var mySearchViewEditText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBoardBinding.inflate(inflater,container,false)
        mBinding = binding

        //게시판 API
        RetrofitManager.instance.getBoardList(completion = {
                   reponseState,responseBody ->
                    when(reponseState){
                   RESPONSE_STATE.OKAY ->{
                       Log.d(TAG, "게시판 로딩 성공 : ${reponseState} ")
                       boardList = responseBody!!
                       mBinding!!.boardRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                       this.boardRecyclerViewAdapter = BoardRecyclerViewAdapter(this)
                       this.boardRecyclerViewAdapter.submitList(boardList)
                       mBinding!!.boardRecyclerView.adapter = this.boardRecyclerViewAdapter
                   }
                   RESPONSE_STATE.CHECK ->{
                       Log.d(TAG, "게시판 로딩 실패: ${reponseState} ")
                       Toast.makeText(App.instance,"게시판 로딩 실패",Toast.LENGTH_SHORT).show()
                   }
                   RESPONSE_STATE.FAIL -> {
                       Log.d(TAG, "Server Closed")
                       Toast.makeText(App.instance,"Server closed",Toast.LENGTH_SHORT).show()
                   }

               }

           })

        mBinding!!.floatingActionButton.setOnClickListener{
            Log.d(TAG, "onClick: FloatingBtn Click")
            var intent = Intent(activity, BoardPostActivity::class.java)
            startActivity(intent)
        }
        mBinding!!.searchHistoryMode.setOnCheckedChangeListener(this)
        mBinding!!.clearSearchHistoryButton.setOnClickListener(this)
        mBinding!!.searchHistoryMode.isChecked = SharedPreferenceManager.checkSearchHistoryList()
        (activity as AppCompatActivity).setSupportActionBar(mBinding!!.topAppBarBoardSearchMenu)
        setHasOptionsMenu(true)
        this.searchHistory = SharedPreferenceManager.getBoardSearchHistoryList() as ArrayList<SearchBoardData>
        handleSearchRecyclerViewUI()
        boardSearchHistoryRecyclerViewSetting(this.searchHistory)
        return mBinding!!.root
    }

    private fun boardSearchHistoryRecyclerViewSetting(boardSearchHistoryList: ArrayList<SearchBoardData>){
        this.boardSearchHistoryRecyclerViewAdapter = BoardSearchHistoryRecyclerViewAdapter(this)
        this.boardSearchHistoryRecyclerViewAdapter.submitList(boardSearchHistoryList)
        val searchHistoryLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, true)
        searchHistoryLayoutManager.stackFromEnd = true
        mBinding!!.searchHistoryRecyclerView.apply{
            layoutManager = searchHistoryLayoutManager
            this.scrollToPosition(boardSearchHistoryRecyclerViewAdapter.itemCount-1)
            adapter = boardSearchHistoryRecyclerViewAdapter

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.top_app_bar_menu,menu)
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        this.mySearchView = menu?.findItem(R.id.search_menu_item)?.actionView as SearchView
        this.mySearchView.apply{
            this.queryHint = "검색어를 입력해주세요"
            this.setOnQueryTextListener(this@BoardFragment)
            this.setOnQueryTextFocusChangeListener { _, hasExpaned ->
                when(hasExpaned){
                    true ->{
                        Log.d(TAG, "서치뷰 열림")
                        mBinding!!.linearSearchHistoryView.visibility = View.VISIBLE
                        mBinding!!.floatingActionButton.visibility = View.INVISIBLE
                        handleSearchRecyclerViewUI()
                    }
                    false ->{
                        Log.d(TAG, "서치뷰 닫힘")
                        mBinding!!.floatingActionButton.visibility = View.VISIBLE
                        mBinding!!.linearSearchHistoryView.visibility = View.INVISIBLE
                    }
                }
            }
            //mySearchViewEditText = this.findViewById(androidx.appcompat.R.id.search_src_text)
        }
        /*
        this.mySearchViewEditText.apply{
            this.filters = arrayOf(InputFilter.LengthFilter(12))
            this.setTextColor(Color.WHITE)
            this.setHintTextColor(Color.WHITE)
        }

         */
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView(){
        mBinding = null
        super.onDestroyView()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrBlank()){
            mBinding!!.topAppBarBoardSearchMenu.title = query

            //TODO : api호출

            //TODO : 검색어 저장
            val newSearchData = SearchBoardData(term=query, timestamp = Date().toSimpleString())
            this.searchHistory.add(newSearchData)
            SharedPreferenceManager.storeBoardSearchHistoryList(this.searchHistory)
            RetrofitManager.instance.getKeywordBoardList(query,completion = {
                    reponseState,responseBody ->
                when(reponseState){
                    RESPONSE_STATE.OKAY ->{
                        Log.d(TAG, "게시판 검색 로딩 성공 : ${reponseState} ")
                        boardList = responseBody!!
                        mBinding!!.boardRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        this.boardRecyclerViewAdapter = BoardRecyclerViewAdapter(this)
                        this.boardRecyclerViewAdapter.submitList(boardList)
                        this.boardRecyclerViewAdapter.notifyDataSetChanged()
                        mBinding!!.boardRecyclerView.adapter = this.boardRecyclerViewAdapter
                        mBinding!!.topAppBarBoardSearchMenu.collapseActionView()
                    }
                    RESPONSE_STATE.CHECK ->{
                        Log.d(TAG, "게시판 로딩 실패: ${reponseState} ")
                        Toast.makeText(App.instance,"게시판 로딩 실패",Toast.LENGTH_SHORT).show()
                    }
                    RESPONSE_STATE.FAIL -> {
                        Log.d(TAG, "Server Closed")
                        Toast.makeText(App.instance,"Server closed",Toast.LENGTH_SHORT).show()
                    }

                }

            })
        }
        mBinding!!.topAppBarBoardSearchMenu.collapseActionView()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val userInputText = newText ?: ""
        if(userInputText.count()==12){
            Toast.makeText(App.instance,"검색어는 12자 까지만 입력 가능합니다",Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onCheckedChanged(switch: CompoundButton?, isChecked: Boolean) {
        when(switch){
            mBinding!!.searchHistoryMode ->{
                if(isChecked){
                    SharedPreferenceManager.setSearchHistoryMode(true)
                    mBinding!!.searchHistoryRecyclerView.visibility =View.VISIBLE
                    mBinding!!.searchHistoryRecyclerViewLabel.visibility =View.VISIBLE
                    Log.d(TAG, "onCheckedChanged : 검색어 저장 기능 on")
                }
                else{
                    SharedPreferenceManager.setSearchHistoryMode(false)
                    mBinding!!.searchHistoryRecyclerView.visibility =View.INVISIBLE
                    mBinding!!.searchHistoryRecyclerViewLabel.visibility =View.INVISIBLE
                    Log.d(TAG, "onCheckedChanged: 검색어 저장 기능 off")
                }
            }

        }
    }

    override fun onClick(v: View?) {
        when(v){
            mBinding!!.clearSearchHistoryButton ->{
                Log.d(TAG, "onClick: 검색어 삭제")
                SharedPreferenceManager.clearSearchHistoryList()
                this.searchHistory.clear()
                handleSearchRecyclerViewUI()
            }

        }
    }

    override fun onSearchItemDeleteBtnClicked(position: Int) {
        //position번째 검색기록 삭제
        this.searchHistory.removeAt(position)
        SharedPreferenceManager.storeBoardSearchHistoryList(this.searchHistory)
        this.boardSearchHistoryRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onSearchItemClicked(position: Int) {
        //해당 position의 검색어로 API 호출
        RetrofitManager.instance.getKeywordBoardList(searchHistory[position].term,completion = {
                reponseState,responseBody ->
            when(reponseState){
                RESPONSE_STATE.OKAY ->{
                    Log.d(TAG, "게시판 검색 로딩 성공 : ${reponseState} ")
                    this.boardList = responseBody!!
                    mBinding!!.boardRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    this.boardRecyclerViewAdapter = BoardRecyclerViewAdapter(this)
                    this.boardRecyclerViewAdapter.submitList(this.boardList)
                    this.boardRecyclerViewAdapter.notifyDataSetChanged()
                    mBinding!!.boardRecyclerView.adapter = this.boardRecyclerViewAdapter
                    mBinding!!.searchHistoryRecyclerView.visibility =View.INVISIBLE
                    mBinding!!.searchHistoryRecyclerViewLabel.visibility =View.INVISIBLE
                    mBinding!!.clearSearchHistoryButton.visibility = View.INVISIBLE
                    mBinding!!.linearSearchHistoryView.visibility=View.INVISIBLE
                }
                RESPONSE_STATE.CHECK ->{
                    Log.d(TAG, "게시판 로딩 실패: ${reponseState} ")
                    Toast.makeText(App.instance,"게시판 로딩 실패",Toast.LENGTH_SHORT).show()
                }
                RESPONSE_STATE.FAIL -> {
                    Log.d(TAG, "Server Closed")
                    Toast.makeText(App.instance,"Server closed",Toast.LENGTH_SHORT).show()
                }

            }

        })
        mBinding!!.topAppBarBoardSearchMenu.title=this.searchHistory[position].term
        mBinding!!.topAppBarBoardSearchMenu.collapseActionView()
    }
    fun handleSearchRecyclerViewUI(){
        if(this.searchHistory.size>0){
            mBinding!!.searchHistoryRecyclerView.visibility =View.VISIBLE
            mBinding!!.searchHistoryRecyclerViewLabel.visibility =View.VISIBLE
            mBinding!!.clearSearchHistoryButton.visibility = View.VISIBLE
        }
        else{
            mBinding!!.searchHistoryRecyclerView.visibility =View.INVISIBLE
            mBinding!!.searchHistoryRecyclerViewLabel.visibility =View.INVISIBLE
            mBinding!!.clearSearchHistoryButton.visibility = View.INVISIBLE
        }
    }

    override fun onClickBoard(position: Int) {
       r_Detail_Board(boardList[position].id)
    }


    fun r_Detail_Board(param : Long){
        RetrofitManager.instance.getDetailBoard(param,completion = {
                reponseState,responseBody ->
            when(reponseState){
                RESPONSE_STATE.OKAY ->{
                    Log.d(TAG, "게시판 세부정보 불러오기성공 : ${reponseState} ")
                    var result = responseBody!!
                    Log.d(TAG, "r_Detail_Board: ${result.content}")
                    var intent = Intent(activity,BoardContentActivity::class.java)
                    var bundle = Bundle()
                    bundle.putSerializable("board_data",result)
                    intent.putExtra("board_bundle",bundle)
                    startActivity(intent)

                }
                RESPONSE_STATE.CHECK ->{
                    Log.d(TAG, "게시판 로딩 실패: ${reponseState} ")
                    Toast.makeText(App.instance,"게시판 로딩 실패",Toast.LENGTH_SHORT).show()
                }
                RESPONSE_STATE.FAIL -> {
                    Log.d(TAG, "Server Closed")
                    Toast.makeText(App.instance,"Server closed",Toast.LENGTH_SHORT).show()
                }

            }

        })

    }
}