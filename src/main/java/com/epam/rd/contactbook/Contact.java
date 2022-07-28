package com.epam.rd.contactbook;

public class Contact {
    private ContactInfo numberInfo;
    private final Email[] emails = new Email[3];
    private final Social[] links = new Social[5];
    private String contactName;
    private int emailsCounter = 0;
    private int socialCount = 0;


    public Contact(String contactName) {
        this.contactName = contactName;
    }

    public void rename(String newName) {
        if(newName != null && !newName.equals(""))
            contactName = newName;
    }
    public Email addEmail(String localPart, String domain) {
        if (this.emailsCounter < 3) {
            emails[this.emailsCounter] = new Email();
            emails[this.emailsCounter].email = localPart + "@" + domain;
            return emails[this.emailsCounter++];
        } else return null;
    }
    public Email addEpamEmail(String firstname, String lastname) {
        if (this.emailsCounter < 3) {
            emails[this.emailsCounter] = new Email() {
                @Override
                public String getTitle() {
                    return "Epam Email";
                }
                @Override
                public String getValue() {
                    return firstname + "_" +  lastname + "@epam.com";
                }
            };
            return emails[this.emailsCounter++];
        } else return null;
    }
    public ContactInfo addPhoneNumber(int code, String number) {
        if (this.numberInfo == null) {
            numberInfo = new ContactInfo() {
                @Override
                public String getTitle() {
                    return "Tel";
                }
                @Override
                public String getValue() {
                    return "+" + code + " " + number;
                }
            };
            return numberInfo;
        } else return null;
    }
    public Social addTwitter(String twitterId) {
        if (this.socialCount < 5) {
            links[this.socialCount] = new Social() {
                @Override
                public String getTitle() {
                    return "Twitter";
                }
                @Override
                public String getValue() {
                    return twitterId;
                }
            };
            return links[this.socialCount++];
        } else return null;
    }
    public Social addInstagram(String instagramId) {
        if (this.socialCount < 5) {
            links[this.socialCount] = new Social() {
                @Override
                public String getTitle() {
                    return "Instagram";
                }
                @Override
                public String getValue() {
                    return instagramId;
                }
            };
            return links[this.socialCount++];
        } else return null;
    }

    public Social addSocialMedia(String title, String id) {
        if (this.socialCount < 5) {
            links[this.socialCount] = new Social() {
                @Override
                public String getTitle() {
                    return title;
                }
                @Override
                public String getValue() {
                    return id;
                }
            };
            return links[this.socialCount++];
        } else return null;
    }
    public ContactInfo[] getInfo() {
        ContactInfo[] info =
                new ContactInfo[1 + this.emailsCounter
                        + this.socialCount
                        + (this.numberInfo == null ? 0 : 1)];
        int index = -0;
        ContactInfo nameContactInfo = new NameContactInfo() {
            @Override
            public String getTitle() {
                return "Name";
            }
            @Override
            public String getValue() {
                return contactName;
            }
        };
        info[index] = nameContactInfo;
        index++;
        if (this.numberInfo != null) {
            info[index] = numberInfo;
            index++;
        }
        for (ContactInfo el : emails) {
            if (el != null) {
                info[index] = el;
                index++;
            }
        }
        for (ContactInfo el : links) {
            if (el != null) {
                info[index] = el;
                index++;
            }
        }
        return info;
    }
    private class NameContactInfo implements ContactInfo {
        @Override
        public String getTitle() {
            return "Name";
        }
        @Override
        public String getValue() {
            return contactName;
        }
    }
    public static class Email  implements ContactInfo {
        private String email;
        @Override
        public String getTitle() {
            return "Email";
        }
        @Override
        public String getValue() {
            return email;
        }
    }
    public static class Social implements ContactInfo {
        private String id;
        String header;
        @Override
        public String getTitle() {
            return header;
        }
        @Override
        public String getValue() {
            return id;
        }
    }
}
