package com.models.daos.interfaces;

import com.models.entities.BanForum;
import java.util.List;


public interface IBanForumDAO {
    
    public boolean setBanForum(BanForum banforum);
    
    public boolean supprimerBanForum(int idBan);
    
    public boolean modifierBanForum( BanForum banforum);
    
    public List<BanForum> getallBanForum();
    
    public BanForum getBanForumbyid(int id);
    
    public List<BanForum> getBanForumbyuser(int idUser);
    
    public List<BanForum> getBanForumbycause(String cause);
}
