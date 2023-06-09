package woks.woks.matthew.quest;

import org.bukkit.Material;

import static woks.woks.WOKS.questManager;

public class MakeQuestAsItem {
    private String name;
    private Integer integerId;
    private String stringId;
    private Material material;
    private String description;

    private String requirements;
    private Quest questSelf;


    public MakeQuestAsItem(Quest quest) {
        this(quest.getName(), quest.getMaterial(), quest.getDescription(), quest.getQuestIntegerId(), quest.getQuestId(), quest.getRequirements(), quest);
    }

    public MakeQuestAsItem(String name, Material material, String description, Integer integerId, String stringId, String requirements) {
        this(name, material, description, integerId, stringId, requirements, questManager.getQuestById(stringId));
    }

    public MakeQuestAsItem(String name, Material material, String description, Integer integerId, String stringId, String requirements, Quest questSelf) {
        this.name = name;
        this.material = material;
        this.description = description;
        this.integerId = integerId;
        this.stringId = stringId;
        this.requirements = requirements;
        this.questSelf = questSelf;
    }

    // quest self


    public Quest getQuestSelf() {
        return questSelf;
    }

    // this should be illegal, cus I heavily dislike it
    public void setQuestSelf(Quest questSelf) {
        this.questSelf = questSelf;
    }

    //req
    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    //nam
    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }


    // mat
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material newMaterial) {
        this.material = newMaterial;
    }

    // desc
    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    //strID
    public String getStringId() {
        return stringId;
    }

    public void setStringId(String newStringId) {
        this.stringId = newStringId;
    }

    //intID
    public Integer getIntegerId() {
        return integerId;
    }

    public void setIntegerId(Integer newIntegerId) {
        this.integerId = newIntegerId;
    }
}
