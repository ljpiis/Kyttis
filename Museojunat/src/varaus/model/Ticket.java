package varaus.model;

/**
 * 
 * Ajattelin, ett‰ Ticket-luokan olion luonti k‰visi n‰in:
 * 1. k‰ytt‰j‰ aloittaa lipun varaamisen. t‰m‰ luo UserThread-olion.
 * 2. UserThread ker‰‰ itseens‰ kaikki lipun tarvitsemat tiedot (omistaja,
 * lipun id, aika, asemat jne.)
 * 3. lopuksi UserThread luo Ticket-olion ker‰‰mill‰‰n tiedoilla. Lippu/liput lis‰t‰‰n
 * asianomaisen User-olion tickets-listaan, ja UserThread lopetetaan.
 * @author Roosa
 *
 */

public class Ticket {
	
	private User owner;
	private int idNumber;
	private boolean isPaid;

}
