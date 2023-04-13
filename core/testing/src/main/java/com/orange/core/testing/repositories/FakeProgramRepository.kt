package com.orange.core.testing.repositories

import com.orange.core.domain.models.Program
import com.orange.core.domain.repositories.ProgramRepository

class FakeProgramRepository : ProgramRepository {
    override suspend fun findProgramsByQuery(query: String): List<Program> {
        val programs = mutableListOf<Program>()

        for (i in 1..50) {
            val program = Program(
                id = i.toString(),
                title = "Titre du programme $i",
                subtitle = "Sous-titre du programme $i",
                pitch = "Pitch du programme $i",
                thumbnailUrl = "https://picsum.photos/200/150?random=$i",
                imageUrl = "https://picsum.photos/600/400?random=$i",
                detailLink = "program-$i",
            )
            programs.add(program)
        }
        return programs
    }

    override suspend fun findProgramDetails(program: Program): Program = Program(
        id = program.id,
        title = program.title,
        subtitle = program.subtitle,
        pitch = "Pitch du programme updated",
        thumbnailUrl = program.thumbnailUrl,
        imageUrl = program.imageUrl,
        detailLink = program.detailLink,
    )
}
