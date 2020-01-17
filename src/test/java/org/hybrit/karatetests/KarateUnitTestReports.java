package org.hybrit.karatetests;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;

import cucumber.api.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import static org.junit.Assert.assertEquals;

@CucumberOptions(features = "classpath:tests")
public class KarateUnitTestReports {

    private static final String KARATE_OUTPUT_PATH = "target/surefire-reports";

    @Test
    public void testParallel() {
        final KarateStats stats = CucumberRunner.parallel(getClass(), 1, KARATE_OUTPUT_PATH);
        generateReport();
        assertEquals("there are scenario failures", 0, stats.getFailCount());
    }

    private static void generateReport() {
        final Collection<File> jsonFiles = FileUtils.listFiles(new File(KARATE_OUTPUT_PATH), new String[] {"json"}, true);

        final List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));

        final ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, new Configuration(new File("target"), "karate-tests"));
        reportBuilder.generateReports();
    }

}
