package services;

import com.monitorjbl.xlsx.StreamingReader;
import daos.BaseDAO;
import exceptions.AppException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import play.Logger;
import play.libs.ws.WSClient;
import play.mvc.Http;
import utils.PdbExecutionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileService extends BaseService {

	SimpleDateFormat sdf_veosat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf_heptan = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat sdf_date_veosat = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sdf_date_heptan = new SimpleDateFormat("dd/M/yy");
	SimpleDateFormat sdf_date_summary = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm:ss");
	private LockService lockService;
	private PdbExecutionContext pdbExecutionContext;

	public FileService() {
		this.lockService = new LockService();
	}

	public FileService(WSClient ws, PdbExecutionContext httpExecutionContext) {
		this.lockService = new LockService();
		this.pdbExecutionContext = httpExecutionContext;
	}

	@Override
	BaseDAO getDAO() {
		return null;
	}

	public String create(Http.MultipartFormData.FilePart<Object> body, boolean append) throws AppException {
//		String status = "error";
//		Logger.info("Start processing new file");
//		Logger.info(body.getFilename());
//		Logger.info(body.getKey());
//		switch (body.getKey()) {
//		case "heptan":
//			if (this.processHeptanFile(body, append))
//				status = "ok";
//			Logger.info("Heptan file processed");
//			break;
//		case "veosat":
//			if (this.processVeosatFile(body, append))
//				status = "ok";
//			Logger.info("Veosat file processed");
//			break;
//		default:
//			Logger.info("No file matchHeptan processing rules");
//			break;
//		}
//		return status;
		return "NO YET IMPLEMENTED";
	}

	// EXCEL PARSER EXAMPLE
	private boolean processHeptanFile(Http.MultipartFormData.FilePart<Object> body, boolean append) throws AppException {
//		if (lockService.findByName(LOCK_HEPTAN_FILE) == null) {
//			lockService.create(LOCK_HEPTAN_FILE);
//		} else {
//			throw new AppException(1, "Already in progress");
//		}
//		try {
//			if (!append) {
//				this.heptanDAO.deleteAll();
//				this.memHeptanDAO.deleteAll();
//			}
//			Workbook workbook = getWorkbook(body);
//			Iterator<Row> iterator = workbook.getSheetAt(0).iterator();
//			Row currentRow;
//			iterator.next(); // omit first row
//			while (iterator.hasNext()) {
//				currentRow = iterator.next();
//				this.heptanDAO.save(new Heptan(this.getCellStringValue(currentRow.getCell(0)), this.getCellStringValue(currentRow.getCell(1)), this.getCellStringValue(currentRow.getCell(2)), this.getCellStringValue(currentRow.getCell(3)),
//						this.getCellStringValue(currentRow.getCell(4)), this.getCellStringValue(currentRow.getCell(5)), this.getCellStringValue(currentRow.getCell(6)), this.getCellStringValue(currentRow.getCell(7)),
//						this.getCellStringValue(currentRow.getCell(8)), this.getCellStringValue(currentRow.getCell(11)), this.getCellStringValue(currentRow.getCell(12)),
//						StringUtils.sanitizeLicensePlate(this.getCellStringValue(currentRow.getCell(15))), this.getCellDateValue(currentRow.getCell(13)).getTime(), this.getCellStringValue(currentRow.getCell(14)),
//						this.getCellFloatValue(currentRow.getCell(9)), this.getCellFloatValue(currentRow.getCell(10))));
//			}
//			lockService.delete(LOCK_HEPTAN_FILE);
//			return true;
//		} catch (Exception e) {
//			lockService.delete(LOCK_HEPTAN_FILE);
//			Logger.debug(e.getMessage(), e);
//			return false;
//		}
		return false;
	}

	private String getCellStringValue(Cell cell) throws Exception {
		if (cell == null)
			return "";
		if (cell.getCellTypeEnum() == CellType.STRING)
			return cell.getStringCellValue().trim();
		else if (cell.getCellTypeEnum() == CellType.NUMERIC)
			return String.valueOf((int) cell.getNumericCellValue()).trim();
		else if (cell.getCellTypeEnum() == CellType.BLANK)
			return "";
		throw new Exception("Cell parse fails");
	}

	private float getCellFloatValue(Cell cell) throws Exception {
		if (cell == null)
			return 0;
		if (cell.getCellTypeEnum() == CellType.STRING)
			return Float.valueOf(cell.getStringCellValue()).floatValue();
		else if (cell.getCellTypeEnum() == CellType.NUMERIC)
			return (float) cell.getNumericCellValue();
		return 0;
	}

	private Date getCellDateValue(Cell cell) throws Exception {
		if (cell == null)
			throw new NullPointerException("Cell is null");
		if (cell.getCellTypeEnum() == CellType.NUMERIC)
			return cell.getDateCellValue();
		else if (cell.getCellTypeEnum() == CellType.STRING)
			return sdf_date_heptan.parse(cell.getStringCellValue());
		throw new Exception("Cell parse fails");
	}

	private long dataStringToEpoch(String date, byte format) {
		try {
			switch (format) {
			case 1:
				return sdf_veosat.parse(date).getTime();
			case 2:
				return sdf_heptan.parse(date).getTime();
			default:
				return 0;
			}
		} catch (ParseException e) {
			Logger.debug(e.getMessage(), e);
			return 0;
		}
	}

	private Workbook getWorkbook(Http.MultipartFormData.FilePart<Object> body) throws FileNotFoundException {
		InputStream is = new FileInputStream((File) body.getFile());
		return StreamingReader.builder().rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
				.bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
				.open(is);
	}



	public String process(int tolerance, int reprocess) throws AppException {
//		try {
//			Logger.info(org.joda.time.Instant.now().toString() + "#" + " START TAPS PROCESS");
//			if (lockService.findByName(LOCK_DATA_PROCESS) == null) {
//				lockService.create(LOCK_DATA_PROCESS);
//			} else {
//				throw new AppException(1, "Data process in progress");
//			}
//			this.initMemVeosat();
//			this.initMemHeptan();
//			Geofence geofence = new Geofence();
//			geofence.setRadius(tolerance);
//			this.currentTolerance = tolerance;
//			List<BaseModel> data = Arrays.asList(this.memVeosatDAO.findNotProcessed());
//			Logger.info(org.joda.time.Instant.now().toString() + "#" + " Not processed: " + data.size());
//			Logger.info(org.joda.time.Instant.now().toString() + "#" + " Reprocess?: " + reprocess);
//			if (reprocess != 0)
//				data = this.memVeosatDAO.findAll();
//			Logger.info(org.joda.time.Instant.now().toString() + "#" + " Final data: " + data.size());
//			if (data.size() == 0) {
//				Logger.info(org.joda.time.Instant.now().toString() + "#" + " Nothing to do. Exit");
//				lockService.delete(LOCK_DATA_PROCESS);
//				return "FINISH. STATUS: 1";
//			}
//			// EVALUATE ALL
//			for (BaseModel memVeosat : data) {
//				//                Logger.info("#########################################################");
//				//                Logger.info("[" + ((MemVeosat) memVeosat).getMatricula() + " at " + sdf_heptan.format(((MemVeosat) memVeosat).getFecha()) + "]");
//				BaseModel[] heptanListForLocationAndDateAndMatricula = findMemHeptanListByLocationAndDateAndMatricula((MemVeosat) memVeosat);
//				BaseModel[] heptanListForLocationAndDate = findMemHeptanListByLocationAndDate((MemVeosat) memVeosat);
//				BaseModel[] heptanListForLocation = findMemHeptanListByLocation((MemVeosat) memVeosat);
//				BaseModel[] basesList = basesService.findBasesByLocation((MemVeosat) memVeosat);
//				BaseModel[] terminalsList = terminalService.findTerminalsByLocation((MemVeosat) memVeosat);
//				//                Logger.info("Founded heptan registry for pos, date and plate: " + heptanListForLocationAndDateAndMatricula.length);
//				//                for (BaseModel item : heptanListForLocationAndDateAndMatricula){
//				//                    Logger.info(((MemHeptan)item).toString());
//				//                }
//				//                Logger.info("###################");
//				//                Logger.info("Founded heptan registry for pos, date: " + heptanListForLocationAndDate.length);
//				//                for (BaseModel item : heptanListForLocationAndDate){
//				//                    Logger.info(((MemHeptan)item).toString());
//				//                }
//				//                Logger.info("###################");
//				//                Logger.info("Founded heptan registry for pos: " + heptanListForLocation.length);
//				//                for (BaseModel item : heptanListForLocation){
//				//                    Logger.info(((MemHeptan)item).toString());
//				//                }
//				//                Logger.info("###################");
//				HeptanMatch dataSetForLocationAndDateAndMatricula, dataSetForLocation, dataSetForLocationAndDate, dataSetForBases = null, dataSetForTerminals = null;
//				boolean inside;
//				dataSetForLocationAndDateAndMatricula = isInsideCloselyDataSet(geofence, (MemVeosat) memVeosat, heptanListForLocationAndDateAndMatricula);
//				inside = dataSetForLocationAndDateAndMatricula.isInside();
//				//                Logger.info("Inside: " + dataSetForLocationAndDateAndMatricula.isInside());
//				dataSetForBases = isInsideBase(geofence, (MemVeosat) memVeosat, basesList);
//				dataSetForTerminals = isInsideTerminal(geofence, (MemVeosat) memVeosat, terminalsList);
//				if (!inside) {
//					inside = dataSetForBases.isInside();
//					//                    Logger.info("Inside base: " + dataSetForBases.isInside());
//					if (!inside) {
//						inside = dataSetForTerminals.isInside();
//						//                        Logger.info("Inside terminal: " + dataSetForBases.isInside());
//					}
//				}
//				dataSetForLocation = isInsideDataSet(geofence, (MemVeosat) memVeosat, heptanListForLocation);
//				//                Logger.info("Sin albaran: " + dataSetForLocation.isInside());
//				dataSetForLocationAndDate = isInsideDataSet(geofence, (MemVeosat) memVeosat, heptanListForLocationAndDate);
//				//                Logger.info("Camion incorrecto: " + dataSetForLocationAndDate.isInside());
//				updateVeosatWithId((MemVeosat) memVeosat, inside, dataSetForLocation, dataSetForLocationAndDate, dataSetForLocationAndDateAndMatricula, dataSetForBases, dataSetForTerminals);
//				//                Logger.info("#########################################################");
//			}
//			// GENERATE SUMMARY
//			Logger.info(org.joda.time.Instant.now().toString() + "#" + " Generating summary...");
//			generateSummary();
//			Logger.info(org.joda.time.Instant.now().toString() + "#" + " Done. Unlock data process");
//			lockService.delete(LOCK_DATA_PROCESS);
//			Logger.info(org.joda.time.Instant.now().toString() + "#" + "FINISH TAPS PROCESS");
//		} catch (ParseException ex) {
//			lockService.delete(LOCK_DATA_PROCESS);
//			Logger.info(org.joda.time.Instant.now().toString() + "#" + "FINISH TAPS PROCESS WITH ERRROR");
//			throw new AppException(ex.getMessage());
//		}
		return "NOT YET IMPLEMENTED";
	}
}
