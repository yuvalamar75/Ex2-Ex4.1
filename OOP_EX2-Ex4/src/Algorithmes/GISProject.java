package Algorithmes;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.Meta_data;

/*
 * This class implements GIS_layer interface.
 * the class contains Set of GISLAyer and has all set functions.
 * the function also has function that returns the Meta_Data of the Project.
 */
public class GISProject implements GIS_project {
	private Set<GIS_layer> project;
	
	public GISProject (Set<GIS_layer>project) {
		this.project=project;
	}
	@Override
	public boolean add(GIS_layer e) {
		return project.add(e); 
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		return project.addAll(c);
	}

	@Override
	public void clear() {
		project.clear();
		
	}

	@Override
	public boolean contains(Object o) {
		return project.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return project.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return project.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return project.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return project.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return project.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return project.retainAll(c);
	}

	@Override
	public int size() {
		return project.size();
	}

	@Override
	public Object[] toArray() {
		return project.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return project.toArray(a);
	}
	
	
	public Set<GIS_layer> getList (){
		return this.project;
	}
	
	@Override
	public Meta_data get_Meta_data() {
		GISLayerProjectMD lpmd=new GISLayerProjectMD();
		
		return lpmd;
	}

}