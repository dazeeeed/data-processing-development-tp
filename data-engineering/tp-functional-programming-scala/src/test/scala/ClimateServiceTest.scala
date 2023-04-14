import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
	test("containsWordGlobalWarming - non climate related words should return false") {
		assert( ClimateService.isClimateRelated("pizza") == false)
		assert( !ClimateService.isClimateRelated("climate is important"))
		assert( !ClimateService.isClimateRelated("icpc"))
		assert( !ClimateService.isClimateRelated("global change"))
		assert( !ClimateService.isClimateRelated("globally change"))
		assert( !ClimateService.isClimateRelated("global, change"))
	}

	test("isClimateRelated - climate related words should return true") {
		assert(ClimateService.isClimateRelated("climate change") == true)
		assert(ClimateService.isClimateRelated("IPcc"))
		assert(ClimateService.isClimateRelated("Climate Change"))
		assert(ClimateService.isClimateRelated("global warming is real"))
		assert(ClimateService.isClimateRelated("aliens caused global warming on the world"))

	}

	test("parseRawData") {
		// Create sample co2records
		val firstRecord = (2003, 1, 355.2)     //help: to acces 2003 of this tuple, you can do firstRecord._1
		val secondRecord = (2004, 1, 375.2)
		val list1 = List(firstRecord, secondRecord)

		// our output of our method "parseRawData"
		val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
		val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
		val output = List(Some(co2RecordWithType), Some(co2RecordWithType2))

		// we call our function here to test our input and output
		assert(ClimateService.parseRawData(list1) == output)
	}

	test("getMinMaxByYear - test temperatures") {
		// Create sample co2records
		val list = List(
			CO2Record(2003, 1, 355.2),
			CO2Record(2004, 1, 375.2),
			CO2Record(2003, 1, 100.0),
			CO2Record(2003, 1, 500.0),
			CO2Record(2004, 1, 50.0),
			CO2Record(2004, 1, 600.0))

		// Expected min/max output
		val output2003 = (100.0, 500.0)
		val output2004 = (50.0, 600.0)

		assert(ClimateService.getMinMaxByYear(list, 2003) == output2003)
		assert(ClimateService.getMinMaxByYear(list, 2004) == output2004)
	}

	test("filterDecemberData") {
		// Create sample co2records
		val co2records = List(
			Option(CO2Record(2003, 12, 355.2)),
			Option(CO2Record(2004, 12, 375.2)),
			Option(CO2Record(2003, 12, 100.0)),
			Option(CO2Record(2003, 1, 500.0)),
			Option(CO2Record(2004, 3, 50.0)),
			Option(CO2Record(2004, 5, 600.0)))

		// Expected results are records without december
		val expectedOutput = List(
			CO2Record(2003, 1, 500.0),
			CO2Record(2004, 3, 50.0),
			CO2Record(2004, 5, 600.0))

		assert(ClimateService.filterDecemberData(co2records) == expectedOutput)
	}
}
