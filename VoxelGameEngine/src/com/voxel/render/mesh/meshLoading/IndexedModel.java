package com.voxel.render.mesh.meshLoading;

import glib.util.vector.GVector2f;
import glib.util.vector.GVector3f;

import java.util.ArrayList;

public class IndexedModel{
	private ArrayList<GVector3f> m_positions;
	private ArrayList<GVector2f> m_texCoords;
	private ArrayList<GVector3f> m_normals;
	private ArrayList<GVector3f> m_tangents;
	private ArrayList<Integer>  m_indices;

	public IndexedModel(){
		m_positions = new ArrayList<GVector3f>();
		m_texCoords = new ArrayList<GVector2f>();
		m_normals = new ArrayList<GVector3f>();
		m_tangents = new ArrayList<GVector3f>();
		m_indices = new ArrayList<Integer>();
	}

	public void calcNormals(){
		for(int i = 0; i < m_indices.size(); i += 3){
			int i0 = m_indices.get(i);
			int i1 = m_indices.get(i + 1);
			int i2 = m_indices.get(i + 2);

			GVector3f v1 = m_positions.get(i1).sub(m_positions.get(i0));
			GVector3f v2 = m_positions.get(i2).sub(m_positions.get(i0));

			GVector3f normal = v1.Cross(v2).normalize();

			m_normals.get(i0).set(m_normals.get(i0).add(normal));
			m_normals.get(i1).set(m_normals.get(i1).add(normal));
			m_normals.get(i2).set(m_normals.get(i2).add(normal));
		}

		for(int i = 0; i < m_normals.size(); i++)
			m_normals.get(i).set(m_normals.get(i).normalize());
	}

	public void calcTangents()
	{
		for(int i = 0; i < m_indices.size(); i += 3)
		{
			int i0 = m_indices.get(i);
			int i1 = m_indices.get(i + 1);
			int i2 = m_indices.get(i + 2);

			GVector3f edge1 = m_positions.get(i1).sub(m_positions.get(i0));
			GVector3f edge2 = m_positions.get(i2).sub(m_positions.get(i0));

			float deltaU1 = m_texCoords.get(i1).getX() - m_texCoords.get(i0).getX();
			float deltaV1 = m_texCoords.get(i1).getY() - m_texCoords.get(i0).getY();
			float deltaU2 = m_texCoords.get(i2).getX() - m_texCoords.get(i0).getX();
			float deltaV2 = m_texCoords.get(i2).getY() - m_texCoords.get(i0).getY();

			float dividend = (deltaU1*deltaV2 - deltaU2*deltaV1);
			//TODO: The first 0.0f may need to be changed to 1.0f here.
			float f = dividend == 0 ? 0.0f : 1.0f/dividend;

			GVector3f tangent = new GVector3f(0,0,0);
			tangent.setX(f * (deltaV2 * edge1.getX() - deltaV1 * edge2.getX()));
			tangent.setY(f * (deltaV2 * edge1.getY() - deltaV1 * edge2.getY()));
			tangent.setZ(f * (deltaV2 * edge1.getZ() - deltaV1 * edge2.getZ()));

			m_tangents.get(i0).set(m_tangents.get(i0).add(tangent));
			m_tangents.get(i1).set(m_tangents.get(i1).add(tangent));
			m_tangents.get(i2).set(m_tangents.get(i2).add(tangent));
		}

		for(int i = 0; i < m_tangents.size(); i++)
			m_tangents.get(i).set(m_tangents.get(i).normalize());
	}

	public ArrayList<GVector3f> getPositions() { return m_positions; }
	public ArrayList<GVector2f> getTexCoords() { return m_texCoords; }
	public ArrayList<GVector3f> getNormals() { return m_normals; }
	public ArrayList<GVector3f> getTangents() { return m_tangents; }
	public ArrayList<Integer>  getIndices() { return m_indices; }
}