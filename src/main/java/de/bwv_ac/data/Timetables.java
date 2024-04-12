package de.bwv_ac.data;

import de.bwv_ac.data.logics.MySlot;
import de.bwv_ac.data.logics.ViewSlot;
import de.bwv_ac.util.Observer;
import de.bwv_ac.util.Subject;

import java.util.*;


/**
 * {@link Timetable} Handler
 *
 * @author Philipp Xhonneux
 * @version 1.1.0
 */
public class Timetables extends Subject implements DataCollection<ViewSlot> {

	/**
	 * List of {@link Timetable} for {@link Company}
	 */
	@SuppressWarnings("FieldMayBeFinal")
	private ArrayList<ViewSlot> timetables = new ArrayList<>();

	/**
	 * {@link String}[] containing the names of columns.
	 * Either the standard names or the names of the CSV-File.
	 * Names from the CSV-File will be set
	 * when using the {@link #add(Collection, String[])} method.
	 */
	//private String[] columns = new String[]{"ID","Firma","Name","Veranstaltung1","Veranstaltung2","Veranstaltung3","Veranstaltung4","Veranstaltung5","Veranstaltung6"};
	private String[] columns = new String[]{"ID","Unternehmen","Veranstaltung","A(08:45-09:30)","B(09:50-10:35)","C(10:35-11:20)","D(11:40-12:25)","E(12:25-13:10)"};

	/**
	 * Create an observable object
	 *
	 * @param c A collection that you want like an ArrayList
	 */
	public Timetables(Collection<Observer> c, Rooms rooms, Wishes wishes) {
		super(c);
	}


	/**
	 * Adds a {@link Timetable} to the inner {@link Collection}<{@link Timetable}>
	 *
	 * @param datastructure that should be added.
	 */
	@Override
	public void add(ViewSlot datastructure) {
		this.timetables.add(datastructure);
		notifyObservers();
	}

	/**
	 * Adds alle elements of the given {@link Collection}<{@link Timetable}> to the inner {@link Collection}<{@link Timetable}>
	 * and set the {@link String}[] for the columns names.
	 *
	 * @param datastructures {@link Collection} of the {@link Timetable} that should be added.
	 * @param columns        names of the columns.
	 */
	@Override
	public void add(Collection<ViewSlot> datastructures, String[] columns) {
		this.timetables.addAll(datastructures);
		this.columns = columns;
		notifyObservers();
	}

	/**
	 * Removes the given {@link Timetable} from the inner {@link Collection}<{@link Timetable}>
	 *
	 * @param datastructure that should be removed.
	 */
	@Override
	public void remove(ViewSlot datastructure) {
		this.timetables.remove(datastructure);
		notifyObservers();
	}

	/**
	 * Changes the {@link Timetable} at the given index to the given {@link Timetable} in the inner {@link Collection}<{@link Timetable}>
	 *
	 * @param index         of the {@link Timetable} that should be changed.
	 * @param datastructure to be changed to.
	 */
	@Override
	public void change(int index, ViewSlot datastructure) {
		this.timetables.set(index, datastructure);
		System.out.println(index);
		notifyObservers();
	}

	/**
	 * Gets the {@link Timetable} at the given index
	 *
	 * @param index of the {@link Timetable} to be returned
	 * @return {@link Timetable}
	 */
	@Override
	public ViewSlot get(int index) {
		return this.timetables.get(index);
	}

	/**
	 * Gets an {@link String}[] of the values as {@link String} from the {@link Timetable} of the given index.
	 *
	 * @param index of the {@link Timetable}
	 * @return {@link String}[]
	 */
	@Override
	public String[] getArray(int index) {
		ViewSlot slot = this.timetables.get(index);

		return slot.ToCSVString(";").split(";");

	}

	/**
	 * Gets an Array of {@link String}[] wich contain the values from the {@link Timetable}, of all {@link Timetable}'s.
	 *
	 * @return {@link String}[][]
	 */
	@Override
	public String[][] getArrays() {
		String[][] timetables = new String[this.timetables.size()][];

		for (int i = 0; i< timetables.length; i++){
			timetables[i] = getArray(i);
		}
		return timetables;
	}

	/**
	 * Gets a {@link String}[] wich contains the names of columns for this {@link Timetable}
	 *
	 * @return {@link String}[]
	 */
	@Override
	public String[] getColumns() {
		return columns;
	}

	/**
	 * Gives back the number of {@link Datastructure}'s.
	 *
	 * @return {@link int}
	 */
	@Override
	public int size() {
		return this.timetables.size();
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<ViewSlot> iterator() {
		return this.timetables.iterator();
	}

	/**
	 * Fill the timetable collection with generated data<br>
	 * from PPerEvents and Rooms
	 */
	/*public void generate(PPerEvents pPerEvents, Rooms rooms) throws Exception {
		if(rooms.size() == 0){
			throw new Exception("Keine Räume gefunden!");
		}
		System.out.println("Generating...");

		// Initialisierung
		List<TimeSlot> timeSlots = new ArrayList<>();
		for (Room room : rooms) {
			for (char timeSlot : Arrays.asList('A', 'B', 'C', 'D', 'E')) {
				timeSlots.add(new TimeSlot(room, timeSlot));
			}
		}

		// Schleife über alle Events
		for (PPerEvent pPerEvent : pPerEvents) {
			PPerEvent event = new PPerEvent();
			event.setID(pPerEvent.getID());
			event.setEvent(pPerEvent.getEvent());
			event.setCount(pPerEvent.getCount());

			// Finden geeigneter Räume
			List<Room> suitableRooms = findSuitableRooms(event, rooms.toList());

			// Priorisierung der Räume
			Collections.sort(suitableRooms, new RoomComparator(timeSlots));

			// Platzierung des Events
			boolean placed = false;
			for (Room room : suitableRooms) {
				for (TimeSlot timeSlot : timeSlots) {
					if (timeSlot.isAvailable() && timeSlot.getRoom().equals(room)) {
						if (event.getCount() <= room.getCapacity()) {
							// Platzierung des Events im aktuellen Zeitslot
							timeSlot.addEvent(event);
							placed = true;
							break;
						} else {
							// Platzierung des Events aufteilen
							int numStudentsPlaced = 0;
							while (numStudentsPlaced < event.getCount()) {
								if (timeSlot.isAvailable() && timeSlot.getRoom().equals(room)) {
									int numStudentsToPlace = Math.min(room.getCapacity(), event.getCount() - numStudentsPlaced);
									PPerEvent tmp = new PPerEvent();
									tmp.setID(event.getID());
									tmp.setEvent(event.getEvent());
									tmp.setCount(event.getCount());
									timeSlot.addEvent(tmp);
									numStudentsPlaced += numStudentsToPlace;

									// Suche nach einem geeigneten Zeitslot für den Rest der Schüler
									if (numStudentsPlaced < event.getCount()) {
										timeSlot = findNextAvailableTimeSlot(room, timeSlots);
										if (timeSlot == null) {
											// Kein geeigneter Zeitslot gefunden
											break;
										}
									}
								} else {
									// Suche nach einem geeigneten Raum
									room = findNextAvailableRoom(rooms.toList(), timeSlots);
									if (room == null) {
										// Kein geeigneter Raum gefunden
										break;
									}

									// Suche nach einem geeigneten Zeitslot im neuen Raum
									timeSlot = findNextAvailableTimeSlot(room, timeSlots);
									if (timeSlot == null) {
										// Kein geeigneter Zeitslot gefunden
										break;
									}
								}
							}

							if (numStudentsPlaced == event.getCount()) {
								// Event erfolgreich platziert
								placed = true;
								break;
							}
						}
					}
				}
				if (placed) {
					break;
				}
			}

			// Event konnte nicht platziert werden
			if (!placed) {
				// TODO: Fehlerbehandlung
			}
		}

		for (TimeSlot ts : timeSlots){
			System.out.println(ts);
		}
		// ...

	}*/

	/*private List<Room> findSuitableRooms(PPerEvent event, List<Room> rooms) {
		List<Room> suitableRooms = new ArrayList<>();
		for (Room room : rooms) {
			if (room.getCapacity() >= event.getCount()) {
				suitableRooms.add(room);
			}
		}
		return suitableRooms;
	}*/

	/*public static TimeSlot findNextAvailableTimeSlot(Room room, List<TimeSlot> timeSlots) {
		for (TimeSlot timeSlot : timeSlots) {
			if (timeSlot.isAvailable() && timeSlot.getRoom().equals(room)) {
				return timeSlot;
			}
		}
		return null;
	}*/


	/*class RoomComparator implements Comparator<Room> {
		private final List<TimeSlot> timeSlots;

		public RoomComparator(List<TimeSlot> timeSlots) {
			this.timeSlots = timeSlots;
		}

		@Override
		public int compare(Room room1, Room room2) {
			int availableSlotsRoom1 = countAvailableTimeSlots(room1, timeSlots);
			int availableSlotsRoom2 = countAvailableTimeSlots(room2, timeSlots);
			return availableSlotsRoom2 - availableSlotsRoom1; // Priorisiere Räume mit mehr freien Slots
		}

		private int countAvailableTimeSlots(Room room, List<TimeSlot> timeSlots) {
			int availableSlots = 0;
			for (TimeSlot timeSlot : timeSlots) {
				if (timeSlot.isAvailable() && timeSlot.getRoom().equals(room)) {
					availableSlots++;
				}
			}
			return availableSlots;
		}

	}*/

	/*public static Room findNextAvailableRoom(List<Room> rooms, List<TimeSlot> timeSlots) {
		for (Room room : rooms) {
			for (TimeSlot timeSlot : timeSlots) {
				if (timeSlot.isAvailable() && timeSlot.getRoom().equals(room)) {
					return room;
				}
			}
		}
		return null;
	}*/
	public static Object[] findMyNextAvailableRoom(List<Room> rooms, List<MySlot> timeSlots, int count) {
		// Perfect room not too big (Not work with to many people then go to next for)
		for(Room room : rooms){
			for (MySlot timeSlot : timeSlots) {
				if (timeSlot.isAvailable() && timeSlot.getRoom().equals(room) && room.getCapacity() <= count+5 && room.getCapacity() > count) {
					return new Object[]{room, timeSlot};
				}
			}
		}

		for (Room room : rooms) {
			for (MySlot timeSlot : timeSlots) {
				if (timeSlot.isAvailable() && timeSlot.getRoom().equals(room)) {
					return new Object[]{room, timeSlot};
				}
			}
		}
		return null;
	}


	public void gen(PPerEvents pPerEvents, Rooms rooms, Companies companies){
		List<MySlot> timeslots = new ArrayList<>();
		String[] slots = new String[] {"A", "B", "C", "D", "E"};
		for(Room room : rooms){
			for(String slot : slots){
				MySlot slotO = new MySlot(slot, room);
				timeslots.add(slotO);
			}
		}
		for(PPerEvent pPerEvent : pPerEvents){
			int id = pPerEvent.getID();
			String name = pPerEvent.getEvent();
			int count = pPerEvent.getCount();

			while(count >= 0){
				//Room room = findMyNextAvailableRoom(rooms.toList(), timeslots, count);
				Object[] roomSlot = findMyNextAvailableRoom(rooms.toList(), timeslots, count);
				Room room = (Room) roomSlot[0];
				MySlot slot = (MySlot) roomSlot[1];
				count -= room.getCapacity();
				ArrayList<MySlot> used = new ArrayList<>();
				for (int i = 0; i<timeslots.size(); i++){
					if(timeslots.get(i) == slot){
						slot.setRoom(room);
						slot.setEvent(companies.getByID(id));
					}
				}
			}
			/*while (count > 0) {
				Object[] roomSlot = findMyNextAvailableRoom(rooms.toList(), timeslots, count);
				Room room = (Room) roomSlot[0];
				MySlot slot = (MySlot) roomSlot[1];

				if (room.getCapacity() >= count) {
					count -= room.getCapacity();
					slot.setEvent(companies.getByID(id));
					slot.setRoom(room);
					room.setUsedCapacity(room.getUsedCapacity() + count);
				} else {
					// Alternative Raumbeschaffung bei Überbuchung
					List<Room> alternativeRooms = findAlternativeRooms(rooms.toList(), count);
					if (!alternativeRooms.isEmpty()) {
						Room alternativeRoom = alternativeRooms.get(0);
						count -= alternativeRoom.getCapacity();
						slot.setEvent(companies.getByID(id));
						slot.setRoom(alternativeRoom);
						alternativeRoom.setUsedCapacity(alternativeRoom.getUsedCapacity() + count);
					} else {
						// TODO: Fehlerbehandlung bei fehlender Alternative
						System.out.println("Keine alternativen Räume verfügbar für: " + name);
						// Event kann hier ggf. in eine Warteliste eingetragen werden
					}
				}
			}*/

		}

		// Output

		StringBuilder csvString = new StringBuilder();

		// Add header row
		//csvString.append("Veranstaltung;A;B;C;D;E\n");  // Assuming desired fields

		for(Company company : companies){
			List<MySlot> eventSlots = getSlotsByEvent(company, timeslots);
			MySlot a = contains(eventSlots, "A");
			MySlot b = contains(eventSlots, "B");
			MySlot c = contains(eventSlots, "C");
			MySlot d = contains(eventSlots, "D");
			MySlot e = contains(eventSlots, "E");
			List<MySlot> eSl = new ArrayList<>();
			eSl.add(a);
			eSl.add(b);
			eSl.add(c);
			eSl.add(d);
			eSl.add(e);
			csvString.append(company.getName() + ";");
			for(MySlot s : eSl){
				if(s == null){
					csvString.append(";");
					continue;
				}
				if(s.getRoom() == null){
					csvString.append(";");
					continue;
				}
				//csvString.append(s.getRoom().getRoomname() + "("+s.getTimeSlot()+");");
				String sa = (a!=null && a.getRoom() != null) ? a.getRoom().getRoomname() : "-";
				String sb = (b!=null && b.getRoom() != null) ? b.getRoom().getRoomname() : "-";
				String sc = (c!=null && c.getRoom() != null) ? c.getRoom().getRoomname() : "-";
				String sd = (d!=null && d.getRoom() != null) ? d.getRoom().getRoomname() : "-";
				String se = (e!=null && e.getRoom() != null) ? e.getRoom().getRoomname() : "-";
				ViewSlot viewSlot = new ViewSlot(company.getID(), company.getName(),company.getSpecialty(), sa, sb, sc, sd, se);
				System.out.println(viewSlot.ToCSVString(";"));


			}
			//csvString.append("\n");

		}

		//System.out.println(csvString.toString());
	}

	private MySlot contains(List<MySlot> slots, String slot){
		for (MySlot mySlot : slots){
			if(mySlot.getTimeSlot().equalsIgnoreCase(slot)){
				return mySlot;
			}
		}
		return null;
	}

	private List<MySlot> getSlotsByEvent(Company company, List<MySlot> slots) {
		ArrayList<MySlot> eventSlots = new ArrayList<>();

		for(MySlot slot : slots){
			if(slot.getEvent() == null)
				continue;
			if(slot.getEvent().getID() == company.getID())
				eventSlots.add(slot);
		}
		return eventSlots;
	}

	public List<Room> findAlternativeRooms(List<Room> rooms, int count) {
		List<Room> alternativeRooms = new ArrayList<>();
		for (Room room : rooms) {
			if (room.getCapacity() >= count && room.getUsedCapacity() < room.getCapacity()) {
				alternativeRooms.add(room);
			}
		}
		return alternativeRooms;
	}

}
