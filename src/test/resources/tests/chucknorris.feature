Feature: Chuck Norris API Test

  Background:
    * def baseUrl = 'https://api.chucknorris.io/jokes'

  Scenario: Retrieve random Chuck Norris fact
    Given url baseUrl + '/random'
    When method GET

    And match response contains {id:#string}
    And match response contains {created_at:#string}
    And match response contains {updated_at:#string}
    And match response contains {icon_url:#string}
    And match response contains {url:#string}
    And match response contains {value:#string}
    And match response contains {categories: '#[] #string'}

    
  Scenario: Retrieve specific Chuck Norris fact
    Given url baseUrl + '/ldq6_b4mslm489drbfmd9q'
    When method GET

    * def isValidChuck = Java.type('org.hybrit.karatetests.ChuckUtils').isValidChuck(response.value)
    And assert isValidChuck

    And match response.id == "ldq6_b4mslm489drbfmd9q"
    And match response.created_at == "2020-01-05 13:42:18.823766"
    And match response.updated_at == "2020-01-05 13:42:18.823766"
    And match response.icon_url == "https://assets.chucknorris.host/img/avatar/chuck-norris.png"
    And match response.url == "https://api.chucknorris.io/jokes/ldq6_b4mslm489drbfmd9q"
    And match response.value == "Since 1940, the year Chuck Norris was born, roundhouse kick related deaths have increased 13,000 percent."


  Scenario Outline: Search for Chuck Norris Facts
    Given url baseUrl + '/search'
    And param query = <query>
    When method GET

    * def jokes = response.result
    And match each jokes contains {id:#string}
    And match each jokes contains {created_at:#string}
    And match each jokes contains {updated_at:#string}
    And match each jokes contains {icon_url:#string}
    And match each jokes contains {url:#string}
    And match each jokes contains {value:#string}

    Examples:
      | query      |
      | "kick"     |
      | "property" |