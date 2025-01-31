package com.example.data.mapper

import com.example.data.model.local.FavoriteEntity
import com.example.data.model.remote.AddressDto
import com.example.data.model.remote.ButtonDto
import com.example.data.model.remote.ExperienceDto
import com.example.data.model.remote.MainDataDto
import com.example.data.model.remote.OfferDto
import com.example.data.model.remote.SalaryDto
import com.example.data.model.remote.VacancyDto
import com.example.domain.model.local.Favorite
import com.example.domain.model.remote.Address
import com.example.domain.model.remote.Button
import com.example.domain.model.remote.Experience
import com.example.domain.model.remote.MainData
import com.example.domain.model.remote.Offer
import com.example.domain.model.remote.Salary
import com.example.domain.model.remote.Vacancy

fun FavoriteEntity.toDomain(): Favorite {
    return Favorite(
        vacancyId = this.vacancyId,
        title = this.title,
        town = this.town,
        company = this.company,
        experience = this.experience,
        publishedDate = this.publishedDate,
        createdAt = this.createdAt
    )
}

fun Favorite.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        vacancyId = this.vacancyId,
        title = this.title,
        town = this.town,
        company = this.company,
        experience = this.experience,
        publishedDate = this.publishedDate,
        createdAt = this.createdAt
    )
}

fun AddressDto.toDomain(): Address {
    return Address(
        town = this.town,
        street = this.street,
        house = this.house
    )
}

fun ButtonDto.toDomain(): Button {
    return Button(
        text = this.text
    )
}

fun ExperienceDto.toDomain(): Experience {
    return Experience(
        previewText = this.previewText,
        text = this.text
    )
}

fun SalaryDto.toDomain(): Salary {
    return Salary(
        short = this.short,
        full = this.full
    )
}

fun OfferDto.toDomain(): Offer {
    return Offer(
        id = this.id,
        title = this.title,
        link = this.link,
        button = this.button?.toDomain()
    )
}

fun VacancyDto.toDomain(): Vacancy {
    return Vacancy(
        id = this.id,
        lookingNumber = this.lookingNumber,
        title = this.title,
        address = this.address.toDomain(),
        company = this.company,
        experience = this.experience.toDomain(),
        publishedDate = this.publishedDate,
        isFavorite = this.isFavorite,
        salary = this.salary.toDomain(),
        schedules = this.schedules,
        appliedNumber = this.appliedNumber,
        description = this.description,
        responsibilities = this.responsibilities,
        questions = this.questions
    )
}

fun MainDataDto.toDomain(): MainData {
    return MainData(
        offers = this.offers?.map { it.toDomain() },
        vacancies = this.vacancies?.map { it.toDomain() }
    )
}