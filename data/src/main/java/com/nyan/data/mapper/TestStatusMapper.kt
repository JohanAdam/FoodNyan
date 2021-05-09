package com.nyan.data.mapper

import com.nyan.data.model.test.TestStatusDataModel
import com.nyan.domain.entity.test.TestStatusEntity

class TestStatusMapper {

    fun mapToEntity(testStatusDataModel: TestStatusDataModel) : TestStatusEntity {
        return TestStatusEntity(
            status = testStatusDataModel.status
        )
    }

}