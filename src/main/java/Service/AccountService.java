package Service;

import Model.Account;

import java.util.Optional;

import DAO.AccountDAO;

public class AccountService {

    private AccountDAO accountDAO;

    /*
     * 
     * @Param
     */
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    /*
     * 
     * @Param AccountDAO accountDAO object
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    /*
     * 
     * @Param Account account object
     */
    public Account createAccount(Account account){
        Account foundAccount = accountDAO.getAccountByUsername(account.getUsername());

        if(foundAccount != null || account.getUsername() == null|| account.getPassword() == null|| account.getUsername().trim().isEmpty() || account.getPassword().length() < 4){
            return null;    
        }
        return accountDAO.createAccount(account);
    }

    /*
     * 
     * @Param String username
     * @Param String password 
     */
    public Account authenticateAccount(String username, String password){
        Account account = accountDAO.getAccountByUsername(username);
        // if account not null, passwords match, return account, else null
        return (account != null && account.getPassword().equals(password))? account : null;
    }

    /*
     * 
     * @Param int account ID number
     */
    public Account getAccountById(int account_id){
        Account account = accountDAO.getAccountById(account_id);
        // if account not null, return account, else null
        return (account != null) ? account : null;
    }

}
