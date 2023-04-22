package com.example.symptomease.ui.dashboard

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SymptomDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {

        // Database name
        val DATABASE_NAME = "symptomsDatabase.db"

        // Version number
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1

        // Table(s) name
        val SAVED_SYMPTOMS = "savedSymptoms_table"

        // Column names
        val _ID = "id" // _(underscore indicates primary key), it is a convention
        val NAME = "name"
        val DESCRIPTION = "description"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val SQL_CREATE_TABLE =
            "CREATE TABLE ${SAVED_SYMPTOMS} (" +
                    "${_ID} INTEGER PRIMARY KEY," +
                    "${NAME} TEXT," +
                    "${DESCRIPTION} TEXT)"

        p0?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addSymptom(symptom :String, symptomDescription :String){
        val database = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(NAME, symptom)
        contentValues.put(DESCRIPTION, symptomDescription)

        database.insert(SAVED_SYMPTOMS, null, contentValues)
    }

    val viewAllSymptoms : Cursor
        get() {
            val database = this.writableDatabase
            val cursor = database.rawQuery("SELECT * FROM " + SAVED_SYMPTOMS, null)

            return cursor
        }


}