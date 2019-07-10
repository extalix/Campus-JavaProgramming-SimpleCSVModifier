import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.stream.Collectors;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class Manusia {
	private String nama;
	private int umur;
	private double berat;
	private boolean jenisKelamin;

	public Manusia(String nama, int umur, double berat, boolean jenisKelamin)
	{
		this.nama = nama;
		this.umur = umur;
		this.berat = berat;
		this.jenisKelamin = jenisKelamin;
	}

	public String getNama() { return nama; }
	public int getUmur() { return umur; }
	public double getBerat() { return berat; }
	public boolean getJenisKelamin() { return jenisKelamin; }
}

class Manusias implements CSV<Manusia> {
	public void write(Path path, List<Manusia> items) {
		try {
			List<String> toFile = new ArrayList<String>();
			for (Manusia i: items) {
				toFile.add(String.format("%s,%s,%s,%s", i.getNama(), i.getUmur(), i.getBerat(), i.getJenisKelamin()));
			}
			Files.write(path, toFile, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.err.println("IOE E");
		} catch (SecurityException e) {
			System.err.println("SE E");
		}
	}
	
	public List<Manusia> read(Path path) {
		try {
			List<String> source = Files.lines(path).collect(Collectors.toList());
			List<Manusia> fromFile = new ArrayList<Manusia>();
			for (String i: source) {
				List<String> kocheng = Arrays.asList(i.split(","));
				fromFile.add(new Manusia(kocheng.get(0), Integer.parseInt(kocheng.get(1)), Double.parseDouble(kocheng.get(2)), Boolean.parseBoolean(kocheng.get(3))));
			}
			return fromFile;
		} catch (IOException e) {
			System.err.println("IOE E");
		} catch (SecurityException e) {
			System.err.println("SE E");
		}
		return null;
	}
}