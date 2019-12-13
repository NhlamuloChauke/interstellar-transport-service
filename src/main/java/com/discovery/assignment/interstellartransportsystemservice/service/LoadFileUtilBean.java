package com.discovery.assignment.interstellartransportsystemservice.service;

import com.discovery.assignment.interstellartransportsystemservice.utils.LoadFileUtil;
import com.discovery.assignment.interstellartransportsystemservice.model.Planet;
import com.discovery.assignment.interstellartransportsystemservice.model.Route;
import com.discovery.assignment.interstellartransportsystemservice.repositories.PlanetRepository;
import com.discovery.assignment.interstellartransportsystemservice.repositories.RouteRepository;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author nhlamulo
 */
@Component
public class LoadFileUtilBean implements LoadFileUtil {

	@Autowired
	@Qualifier("planet")
	PlanetRepository planetRepository;

	@Autowired
	@Qualifier("route")
	RouteRepository routesRepository;

	@Value(value = "${file.path}")
	private String payloadData;

	static final Logger LOG = org.apache.log4j.Logger.getLogger(LoadFileUtilBean.class);

	@EventListener
	public void onApplicationEvent(ContextStartedEvent startEvent) throws IOException {
		readXlsFile();
	}

	@Override
	public void readXlsFile() throws FileNotFoundException, IOException{
		try {
			FileInputStream xlsFile = new FileInputStream(new File(payloadData));
			Workbook workbook =  new XSSFWorkbook(xlsFile);
			savePlanets(workbook);
			saveRoute(workbook);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private void savePlanets(Workbook workbook)  {
		Sheet planetTab = workbook.getSheetAt(0);
			for(Row row : planetTab){
				Cell idCell = row.getCell(0);
				Cell nameCell = row.getCell(1);
				if ((idCell.getCellTypeEnum() == CellType.STRING)
						&& (nameCell.getCellTypeEnum() == CellType.STRING)) {

					planetRepository.save(new Planet((long) idCell.getNumericCellValue(), nameCell.getStringCellValue()));
					LOG.info(" Planet object saved  -- Planet Node : " + idCell.getStringCellValue() + "   Planet Name : " + nameCell.getStringCellValue() + "}");

				}
			}
		}

	private void saveRoute(Workbook workbook) {
		Sheet sourceTab = workbook.getSheetAt(1);
		for (Row row : sourceTab) {
			Cell idCell = row.getCell(0);
			Cell sourceCell = row.getCell(1);
			Cell destinationCell = row.getCell(2);
			Cell distenceCell = row.getCell(3);

			if (!StringUtils.isEmpty(idCell) && !StringUtils.isEmpty(sourceCell)
					&& !StringUtils.isEmpty(idCell) && !StringUtils.isEmpty(distenceCell)) {

				Planet sourcePlanet = getPlanetBySingleArument(sourceCell.getStringCellValue());
				Planet destinationPlanet = getPlanetBySingleArument(destinationCell.getStringCellValue());
				if (!StringUtils.isEmpty(sourcePlanet) && !StringUtils.isEmpty(destinationPlanet)) {
					routesRepository.save(new Route((long) idCell.getNumericCellValue(), sourcePlanet, destinationPlanet, (float) distenceCell.getNumericCellValue()));
					LOG.info("Saved Route -- {Route ID: " + idCell + "  Source : " + sourcePlanet.getPlanetID() + " Destination : " + destinationPlanet.getPlanetID() + " Distance " + distenceCell.getNumericCellValue() + "}");
				}
			}
		}
	}

	private Planet getPlanetBySingleArument(String singleArg){
		Planet planet = planetRepository.getOne(singleArg);
		return  planet;
	}


}
