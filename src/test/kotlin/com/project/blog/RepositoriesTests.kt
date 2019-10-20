package com.project.blog

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return Article` () {
        val john = User("JohnDoeLogin", "John", "Doe")
        entityManager.persist(john)

        val article = Article(
                "Spring Framework 5.0 goes GA",
                "Dear Spring community ...",
                "Lorem ipsum", john
        )
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)

    }

    @Test
    fun `When findByLogin then return User` () {
        val john = User("JohnDoeLogin", "John", "Doe")
        entityManager.persist(john)
        entityManager.flush()
        val user = userRepository.findByLogin(john.login)
        assertThat(user).isEqualTo(john)

    }

}
