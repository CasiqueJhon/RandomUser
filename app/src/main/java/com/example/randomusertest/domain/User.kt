package com.example.randomusertest.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class RandomUserResponse(
    val results: List<User>,
    val info: Info
): Parcelable
@Parcelize
data class User(
    val gender: String,
    val name: Name,
    val email: String,
    val registered: Registered,
    val phone: String,
    val location: Location,
    val picture: Picture
): Parcelable
@Parcelize
data class Name(
    val title: String,
    val first: String,
    val last: String
): Parcelable
@Parcelize
data class Registered(
    val date: String
): Parcelable
@Parcelize
data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: Coordinates
): Parcelable
@Parcelize
data class Street(
    val number: Int,
    val name: String
): Parcelable
@Parcelize
data class Coordinates(
    val latitude: String,
    val longitude: String
): Parcelable
@Parcelize
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
): Parcelable
@Parcelize
data class Info(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
): Parcelable