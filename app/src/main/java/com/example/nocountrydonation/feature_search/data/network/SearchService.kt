package com.example.nocountrydonation.feature_search.data.network

import com.example.nocountrydonation.feature_donors.domain.Donors
import com.example.nocountrydonation.util.ResultState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class SearchService {
    suspend fun getSearch(query : String?):ResultState<List<Donors>>{
        val listSearch = mutableListOf<Donors>()
        val querys = FirebaseFirestore.getInstance().collection("donantes").whereEqualTo("blood","$query").get().await()
        for (service in querys.documents) {
            val item = service.toObject(Donors::class.java)
            item?.image = service["image"].toString()
            item?.name = service["name"].toString()
            item?.city = service["city"].toString()
            item?.blood = service["blood"].toString()
            item?.hospital = service["hospital"].toString()
            if (item != null) {
                listSearch.add(item)
            }
        }
        return ResultState.Success(listSearch)
    }
}