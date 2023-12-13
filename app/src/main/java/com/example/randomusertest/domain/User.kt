package com.example.randomusertest.domain

data class RandomUserResponse(
    val results: List<User>,
    val info: Info
)

data class User(
    val gender: String,
    val name: Name,
    val email: String,
    val registered: Registered,
    val phone: String,
    val location: Location,
    val picture: Picture
)

data class Name(
    val title: String,
    val first: String,
    val last: String
)

data class Registered(
    val date: String
)

data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: Coordinates
)

data class Street(
    val number: Int,
    val name: String
)

data class Coordinates(
    val latitude: String,
    val longitude: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class Info(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)