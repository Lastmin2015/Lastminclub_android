package ru.vsibi.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vsibi.data.api.auth.AuthRepository
import ru.vsibi.data.api.auth.AuthRepositoryImpl
import ru.vsibi.data.api.auth.AuthService
import ru.vsibi.data.api.hotels.HotelsRepository
import ru.vsibi.data.api.hotels.HotelsRepositoryImpl
import ru.vsibi.data.api.hotels.HotelsService
import ru.vsibi.data.api.profile.ProfileRepository
import ru.vsibi.data.api.profile.ProfileRepositoryImpl
import ru.vsibi.data.api.profile.ProfileService
import ru.vsibi.data.api.search.SearchRepository
import ru.vsibi.data.api.search.SearchRepositoryImpl
import ru.vsibi.data.api.search.SearchService

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideAuthRepository(authService : AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

    @Provides
    fun provideProfileRepository(profileService: ProfileService): ProfileRepository {
        return ProfileRepositoryImpl(profileService)
    }

    @Provides
    fun provideSearchRepository(searchService: SearchService): SearchRepository {
        return SearchRepositoryImpl(searchService)
    }

    @Provides
    fun provideHotelsRepository(hotelsService: HotelsService): HotelsRepository {
        return HotelsRepositoryImpl(hotelsService)
    }

}