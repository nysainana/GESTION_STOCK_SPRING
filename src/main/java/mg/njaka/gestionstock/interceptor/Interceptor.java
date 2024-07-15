package mg.njaka.gestionstock.interceptor;

import org.hibernate.EmptyInterceptor;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.Locale;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {

        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")){

            final String entityName = sql.substring(7, sql.indexOf("."));
            final String idEntreprise = MDC.get("idEtreprise");

            if(StringUtils.hasLength(entityName)
            && !entityName.toLowerCase().contains("entreprise")
            && !entityName.toLowerCase().contains("role")
            && StringUtils.hasLength(idEntreprise)){
                if(sql.contains("where")){
                    sql += " and "+ entityName +".id_entreprise = " + idEntreprise;
                }
                else{
                    sql += " where "+ entityName +".id_entreprise = " + idEntreprise;
                }
            }
        }

        return super.onPrepareStatement(sql);
    }
}
