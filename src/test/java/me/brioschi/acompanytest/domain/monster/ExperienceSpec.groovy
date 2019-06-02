package me.brioschi.acompanytest.domain.monster


import spock.lang.Specification

import java.security.InvalidParameterException

class ExperienceSpec extends Specification {

    def "Experience can't be less than callow"() {

        when:

        new Experience(Experience.CALLOW_VALUE - 1)

        then:

        thrown InvalidParameterException

    }

    def "Experience can be added"() {

        given:

        Experience expA = new Experience(10)
        Experience expB = new Experience(17)

        when:

        Experience res = expA.increase(expB)

        then:

        assert res.getExperience() == 27

    }

    def "Experience can be subtracted"() {

        given:

        Experience expA = new Experience(27)
        Experience expB = new Experience(12)

        when:

        Experience res = expA.decrease(expB)

        then:

        assert res.getExperience() == 15

    }

    def "Experience can't be negative (decrease)"() {

        given:

        Experience expA = new Experience(17)
        Experience expB = new Experience(10)

        when:

        Experience res = expA.decrease(expB)

        then:

        assert res.getExperience() == Experience.CALLOW_VALUE

    }

}
