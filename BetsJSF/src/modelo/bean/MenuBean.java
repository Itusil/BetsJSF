package modelo.bean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;

public class MenuBean {
	private Date fecha;
	private BLFacade bl= new BLFacadeImplementation();
	private List<Event> eventos=new ArrayList<Event>();

	public MenuBean() {
	}

	public List<Event> getEventos(){
		return eventos;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}	
	public void onDateSelectMostrar(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Fecha escogida:"+this.getFecha().toString()));//event.getObject().toString())
	}
	public void onDateSelect(SelectEvent event) {
		eventos=new ArrayList<Event>();
		Vector<Event> j = bl.getEvents(getFecha());
		Iterator<Event> jit = j.iterator();
		while (jit.hasNext()) {
			eventos.add(jit.next());
		}
	}

	public void verPreguntas () {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String id =params.get("evento");
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Id:"+id));//event.getObject().toString())
	}



}