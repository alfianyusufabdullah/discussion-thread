package alfianyusufabdullah.exp.discussion.di

import alfianyusufabdullah.exp.discussion.data.DiscussionRepository
import alfianyusufabdullah.exp.discussion.data.route.DiscussionClient
import alfianyusufabdullah.exp.discussion.domain.Mapper
import alfianyusufabdullah.exp.discussion.domain.usecase.GetAllDiscussionTaskUseCase
import alfianyusufabdullah.exp.discussion.domain.usecase.GetAllDiscussionWithParentIdTaskUseCase
import alfianyusufabdullah.exp.discussion.feature.main.MainViewModel
import alfianyusufabdullah.exp.discussion.feature.thread.ThreadViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { DiscussionClient().clientService }
    single { DiscussionRepository(get()) }
}

val domainModule = module {
    single { Mapper() }
    factory { GetAllDiscussionTaskUseCase(get(), get()) }
    factory { GetAllDiscussionWithParentIdTaskUseCase(get(), get()) }
}

@ExperimentalCoroutinesApi
val presentationModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { ThreadViewModel(get()) }
}