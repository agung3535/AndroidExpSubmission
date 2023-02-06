package com.example.moviesubmissionandroidexp.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.moviesubmissionandroidexp.core.entities.source.local.room.MovieDao
import com.example.moviesubmissionandroidexp.core.entities.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE favorite_movie ADD COLUMN movie_id INTEGER")
            database.execSQL("CREATE TABLE `favorite_movie_new` (`fav_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "`title` TEXT NOT NULL,`backdrop_path` TEXT NOT NULL, `overview` TEXT NOT NULL," +
                    "`popularity` REAL NOT NULL,`vote_average` REAL NOT NULL,`original_language` TEXT NOT NULL," +
                    "`fav_category_id` INTEGER NOT NULL, `movie_id` INTEGER NOT NULL," +
                    "FOREIGN KEY(`fav_category_id`) REFERENCES `favorite_category`(`fav_caretegorymovie_id`) ON UPDATE NO ACTION ON DELETE CASCADE)")
            //COPY DATA
            database.execSQL("INSERT INTO `favorite_movie_new` (fav_id,title,backdrop_path,overview,popularity,vote_average,original_language,fav_category_id," +
                    "movie_id) SELECT * FROM favorite_movie")
            //REMOVE
            database.execSQL("DROP TABLE favorite_movie")
            database.execSQL("ALTER TABLE favorite_movie_new RENAME TO favorite_movie")
        }
    }



    val MIGRATION_2_3: Migration = object : Migration(2,3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `cast_movie` (`castId` INTEGER PRIMARY KEY NOT NULL, `name` TEXT NOT NULL," +
                    "`original_name` TEXT NOT NULL,`profile_path` TEXT,`fk_favid` INTEGER NOT NULL," +
                    "FOREIGN KEY(`fk_favid`) REFERENCES `favorite_movie`(`fav_id`) ON UPDATE NO ACTION ON DELETE CASCADE)")

            database.execSQL("CREATE TABLE `review_movie` (`review_id` INTEGER PRIMARY KEY NOT NULL, `author` TEXT NOT NULL," +
                    "`rating` REAL,`author_image` TEXT,`content` TEXT NOT NULL,`created_at` TEXT NOT NULL,`fk_favid` INTEGER NOT NULL," +
                    "FOREIGN KEY(`fk_favid`) REFERENCES `favorite_movie`(`fav_id`) ON UPDATE NO ACTION ON DELETE CASCADE)")

        }
    }

    val MIGRATION_3_4: Migration = object : Migration(3,4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `temp_delete_movie_fav` (`fav_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "`title` TEXT NOT NULL,`backdrop_path` TEXT NOT NULL, `overview` TEXT NOT NULL," +
                    "`popularity` REAL NOT NULL,`vote_average` REAL NOT NULL,`original_language` TEXT NOT NULL," +
                    "`fav_category_id` INTEGER NOT NULL, `movie_id` INTEGER NOT NULL)")
        }

    }

//    val MIGRATION_3_4: Migration = object : Migration(3,4) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("CREATE TABLE `cast_movie` (`castId` INTEGER PRIMARY KEY NOT NULL, `name` TEXT NOT NULL," +
//                    "`original_name` TEXT NOT NULL,`profile_path` TEXT,`fk_favid` INTEGER NOT NULL," +
//                    "FOREIGN KEY(`fk_favid`) REFERENCES `favorite_movie`(`movie_id`) ON UPDATE NO ACTION ON DELETE CASCADE)")
//
//            database.execSQL("CREATE TABLE `review_movie` (`review_id` INTEGER PRIMARY KEY NOT NULL, `author` TEXT NOT NULL," +
//                    "`rating` REAL,`author_image` TEXT,`content` TEXT NOT NULL,`created_at` TEXT NOT NULL,`fk_favid` INTEGER NOT NULL," +
//                    "FOREIGN KEY(`fk_favid`) REFERENCES `favorite_movie`(`movie_id`) ON UPDATE NO ACTION ON DELETE CASCADE)")
//
//        }
//    }

    val passphrase: ByteArray = SQLiteDatabase.getBytes("simplemovie".toCharArray())
    val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java,"movie.db")
        .addMigrations(MIGRATION_3_4)
            .openHelperFactory(factory)
        .build()


    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()


}