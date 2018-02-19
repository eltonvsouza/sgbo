package br.gov.pb.paraiba.model;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;


import org.hibernate.EntityMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.IdentifierType;

public class ConveterGenerico{
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public final Object getAsObject(final FacesContext context,
			final UIComponent component, final String value) {
		Class targetClass = getTargetClass(context, component);
		if (value.length() == 0) {
			return null;
		}
		ClassMetadata classMetadata = getClassMetadata(targetClass);
		IdentifierType identifierType = (IdentifierType) classMetadata
				.getIdentifierType();
		Serializable identifier = null;
		try {
			identifier = (Serializable) identifierType.stringToObject(value);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Session session = sessionFactory.getCurrentSession();
		return session.get(targetClass, identifier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public final String getAsString(final FacesContext context,
			final UIComponent component, final Object object) {
		Class targetClass = getTargetClass(context, component);
		if (object == null) {
			return "";
		}

		if (!(object.getClass().equals(targetClass))) {
			return "";
		}

		ClassMetadata classMetadata = getClassMetadata(object.getClass());
		return classMetadata.getIdentifier(object, EntityMode.POJO).toString();
	}

	/**
	 * Retorna o tipo do objeto que vai ser convertido.
	 * 
	 * @param context
	 *            um FacesContext encapsulando a requisição web atual
	 * @param component
	 *            o componente JSF cujo valor será convertido
	 * @return o tipo do objeto que vai ser convertido
	 */
	@SuppressWarnings("unchecked")
	private Class getTargetClass(final FacesContext context,
			final UIComponent component) {
		return component.getValueBinding("value").getType(context);
	}

	/**
	 * Retorna os metadados para a classe especificada.
	 * 
	 * @param targetClass
	 *            a classe
	 * @return os metadados para a classe especificada
	 */
	@SuppressWarnings("unchecked")
	private ClassMetadata getClassMetadata(final Class targetClass) {
		return sessionFactory.getClassMetadata(targetClass);
	}

	/**
	 * Seta um SessionFactory contendo a sessionFactory.
	 * 
	 * @param sessionFactory
	 *            um SessionFactory contendo a sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
