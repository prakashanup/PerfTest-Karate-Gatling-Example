import com.intuit.karate.gatling.{KarateFeatureActionBuilder, KarateProtocol}
import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

class PerfTestSimulation extends Simulation {

  // Define the Karate feature file to run
  val featureFile: KarateFeatureActionBuilder = karateFeature("classpath:getRequest.feature")

  // Define the Karate protocol with the correct method call
  val protocol: KarateProtocol = karateProtocol() // Corrected: parentheses added to call the method

  // Define the Gatling scenario
  val getRequestScenario: ScenarioBuilder = scenario("Get Request Performance Test").exec(featureFile)

  // Define the load test with the desired configuration
  setUp(
    getRequestScenario.inject(
      atOnceUsers(10),             // 10 users hit the endpoint immediately
      rampUsers(20) during 30,     // Ramp up to 20 users over 30 seconds
      constantUsersPerSec(2).during(30)
    ).protocols(protocol)    // Karate protocol configuration
  )
}
