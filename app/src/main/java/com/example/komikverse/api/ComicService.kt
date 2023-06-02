package com.example.komikverse.api

import com.example.komikverse.models.Chapter
import com.example.komikverse.models.Comic
import com.example.komikverse.models.Image
import com.example.komikverse.models.PrevNext
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicService {
    @GET("comics/all")
    fun getComicList () : Call<List<Comic>>

    @GET("comics/{comic_id}/chapters/all")
    fun getChapterList (
        @Path(value = "comic_id", encoded = true) comicId: String
    ) : Call<List<Chapter>>

    @GET("comics/{comic_id}/chapters/{chapter_id}/prevnext")
    fun getChapterPrevNext (
        @Path(value = "comic_id", encoded = true) comicId: String,
        @Path(value = "chapter_id", encoded = true) chapterId: String,
    ) : Call<PrevNext>

    @GET("comics/{comic_id}/chapters/{chapter_id}/images/{lang}/all")
    fun getImageList (
        @Path(value = "comic_id", encoded = true) comicId: String,
        @Path(value = "chapter_id", encoded = true) chapterId: String,
        @Path(value = "lang", encoded = true) lang: String,
    ) : Call<List<Image>>
}