package me.brioschi.acompanytest

import spock.lang.Specification

class SimpleTestSpec extends Specification {

    def "A simple test"() {

        expect:

        assert true == true

    }
}
