package lab2_rollins_blake;

public class Lab2Tests {

    public static void main(String[] args) {
        Lab2Tests program = new Lab2Tests();
        program.testValidations();
    }


    public boolean testValidate(ContactList.SortType t, String s, boolean expected) {
        boolean testResult = true;
        boolean actual = true;

        switch (t) {
            case Birthday:
                actual = Contact.validateBirthday(s);
                break;
            case Email:
                actual = Contact.validateEmail(s);
                break;
            case Name:
                actual = Contact.validateName(s);
                break;
            case Phone:
                actual = Contact.validatePhone(s);
                break;
        }
        if (actual == expected) {
            testResult = true;
        } else {
            testResult = false;
        }
        System.out.println(testResult + ": testValidate" + t + ": " + s + " Expected: " + expected + " Actual: " + actual);

        return testResult;
    }

    public boolean testValidations() {

        boolean allTestsHavePassed = true;

        allTestsHavePassed = (testValidate(ContactList.SortType.Birthday, "4/25/06", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Birthday, "2/7/1951", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Birthday, "8-10-1947", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Birthday, "3/16/1868", false)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Birthday, "3.26.1933", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Birthday, "3/26/2016", false)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Birthday, "3.26.17", false)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Email, "cadam21@yahoo.com", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Email, "adamsrib@ktis.net", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Email, "alicearnie@tmobile.com", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Email, "PMACLJA@.com", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Email, "jules_b728@yahoo.com", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Email, "june83@optimum.net", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Email, "d.bish.67933@aol.com", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Name, "Anna Sthesia", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Name, "Rick O'Shea", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Name, "", false)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Name, "Wilma mumduya", false)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Name, "Bud", false)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Name, "Frank N. Stein", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Name, "Frank Nelson Stein", false)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Name, "Frank Nelson. Stein", false)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Phone, "(973) 328-6490", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Phone, "(908) 820-3969", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Phone, "973.680.3518", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Phone, "908-859-5467", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Phone, "899.107.2153", true)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Phone, "899 107 2153", false)) ? allTestsHavePassed : false;
        allTestsHavePassed = (testValidate(ContactList.SortType.Phone, "899-d07-2153", false)) ? allTestsHavePassed : false;

        System.out.println(allTestsHavePassed);
        return allTestsHavePassed;
    }
}
