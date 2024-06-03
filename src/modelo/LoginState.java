package modelo;

public class LoginState {
    private String id, role, name;
    private boolean authenticated;
    public LoginState(String id, String name, String role, boolean authenticated){
        this.id = id;
        this.role = role;
        this.authenticated = authenticated;
        this.name = name;
    }

    public String getId( ){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getRole( ){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public boolean getAuthenticated( ){
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated){
        this.authenticated = authenticated;
    }

    public String getName( ){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
}

