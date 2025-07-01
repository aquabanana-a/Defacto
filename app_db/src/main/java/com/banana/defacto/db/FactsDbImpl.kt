package com.banana.defacto.db

import android.content.Context
import androidx.room.Room
import com.banana.defacto.db.common.NumberType
import com.banana.defacto.db.entity.FactEntity

class FactsDbImpl(
    private val context: Context
): FactsDb {
    companion object {
        private const val DB_NAME = "facts.db"
    }

    private val db =  Room.databaseBuilder(context, FactsDbRoom::class.java, DB_NAME).build()

    override fun getFacts() = db.factsDao().getFacts()

    override fun getFact(number: Int, type: NumberType) = db.factsDao().getFact(number, type)

    override fun insertOrUpdate(entities: List<FactEntity>) = db.factsDao().insertOrUpdate(entities)

    override fun insertOrUpdate(entity: FactEntity) = db.factsDao().insertOrUpdate(entity)
}