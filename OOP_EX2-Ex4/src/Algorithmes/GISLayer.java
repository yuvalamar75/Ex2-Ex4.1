package Algorithmes;

/*
 * This class implements GIS_layer interface.
 * the class contains Set of GIS_Elemnt and has all set functions.
 * the function also has function that returns the Meta_Data of the layer
 */

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Meta_data;


public class GISLayer implements GIS_layer {
	Set<GIS_element> set;

	public GISLayer(Set<GIS_element> set) {
		this.set = set;
	} 

	@Override
	public boolean add(GIS_element arg0) {
		return set.add(arg0);

	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {
		return set.addAll(arg0);
	}

	@Override
	public void clear() {
		set.clear();

	}

	@Override
	public boolean contains(Object arg0) {
		return set.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return set.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return set.iterator();


	}

	@Override
	public boolean remove(Object arg0) {
		return set.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return set.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return set.retainAll(arg0);
	}

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public Object[] toArray() {
		return set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return set.toArray(arg0);
	}

	@Override
	public Meta_data get_Meta_data() {
		GISLayerProjectMD m=new GISLayerProjectMD();

		return m;		
	}

	public Set<GIS_element> getList(){
		return this.set;
	}



}

