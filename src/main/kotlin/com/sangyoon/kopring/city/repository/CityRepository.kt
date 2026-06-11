package com.sangyoon.kopring.city.repository

import com.sangyoon.kopring.city.entity.City
import org.springframework.data.jpa.repository.JpaRepository

interface CityRepository : JpaRepository<City, Long>
